package com.ericlam.mc.factorylib;

import com.ericlam.mc.factorylib.command.Command;
import com.ericlam.mc.factorylib.command.CommandResult;
import com.ericlam.mc.factorylib.command.MainCommand;
import com.ericlam.mc.factorylib.command.SubCommand;
import com.google.inject.Inject;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public final class CommandHandler implements CommandManager, CommandExecutor, TabCompleter {

    private final Map<Class<?>, Object> commandMap = new HashMap<>();

    @Inject
    private Plugin plugin;

    @Override
    public void registerCommand(JavaPlugin plugin, Class<?> command) {
        if (!command.isAnnotationPresent(Command.class)) {
            throw new IllegalStateException("註冊的Class類別沒有標註 @Command");
        }
        Command c = command.getAnnotation(Command.class);
        String cmd = c.command();
        if (cmd.isEmpty()) {
            throw new IllegalStateException("指令不能為空。");
        }

        try {
            Constructor<?> constructor = command.getConstructor();
            constructor.setAccessible(true);
            this.commandMap.put(command, constructor.newInstance());
            PluginCommand pluginCommand = plugin.getCommand(cmd);
            pluginCommand.setDescription(c.description());
            pluginCommand.setPermission(c.permission());
            pluginCommand.setAliases(Arrays.asList(c.alias()));
            pluginCommand.setExecutor(this);
            pluginCommand.setTabCompleter(this);
        } catch (Exception e) {
            e.printStackTrace();
            plugin.getLogger().warning("無法註冊指令 " + cmd + ": " + e.getMessage());
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {

        final String noPerm = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("command-lib.no-perm"));
        final String unknownCmd = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("command-lib.unknown-cmd"));
        final String noArgs = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("command-lib.no-args"));

        for (Class<?> cmdCls : commandMap.keySet()) {
            Command c = cmdCls.getAnnotation(Command.class);
            if (!c.command().equalsIgnoreCase(command.getName())) continue;
            if (!c.permission().isEmpty() && !commandSender.hasPermission(c.permission())) {
                commandSender.sendMessage(String.format(noPerm, c.permission()));
                return true;
            }
            Object ins = commandMap.get(cmdCls);
            List<Method> methods = Arrays.stream(cmdCls.getDeclaredMethods())
                    .filter(m -> strings.length < 1 ? m.isAnnotationPresent(MainCommand.class) : m.isAnnotationPresent(SubCommand.class))
                    .filter(m -> Arrays.equals(m.getParameterTypes(), new Class<?>[]{CommandSender.class, String[].class}))
                    .filter(m -> m.getReturnType() == CommandResult.class)
                    .sorted(Comparator.comparing(Method::getName))
                    .collect(Collectors.toList());
            CommandResult result = CommandResult.RETURN_FALSE;
            try {
                String[] args = strings.clone();
                if (strings.length < 1) {
                    if (methods.isEmpty()) throw new IllegalStateException("找不到 @MainCommand.");
                    MainCommand mainCommand = methods.get(0).getAnnotation(MainCommand.class);
                    if (mainCommand.placeholders().length > 1) {
                        commandSender.sendMessage(String.format(noArgs, String.join(" ", mainCommand.placeholders())));
                        return false;
                    } else {
                        result = (CommandResult) methods.get(0).invoke(ins, commandSender, args);
                        if (result == CommandResult.SHOW_HELP) {
                            commandSender.sendMessage(Arrays.stream(cmdCls.getDeclaredMethods()).
                                    filter(m -> m.isAnnotationPresent(SubCommand.class))
                                    .filter(m -> m.getAnnotation(SubCommand.class).parent().isEmpty())
                                    .map(m -> {
                                        SubCommand sub = m.getAnnotation(SubCommand.class);
                                        return "/" + c.command() + " " + sub.command() + " - " + sub.description();
                                    }).toArray(String[]::new));
                            return true;
                        } else {
                            return result == CommandResult.RETURN_TRUE;
                        }
                    }
                }
                Method finalMethod = null;
                while (args.length != 0) {
                    boolean noCmd = false;
                    for (Method method : methods) {
                        // commandSender.sendMessage(String.join(" ", args)); //
                        SubCommand cmd = method.getAnnotation(SubCommand.class);
                        // commandSender.sendMessage(cmd.command()); //
                        // commandSender.sendMessage(cmd.parent()); //
                        // commandSender.sendMessage(String.format("%s vs %s", cmd.command(), args[0])); //
                        if (cmd.command().equalsIgnoreCase(args[0])) {
                            if (finalMethod != null) {
                                SubCommand parentCmd = finalMethod.getAnnotation(SubCommand.class);
                                // commandSender.sendMessage(String.format("%s vs %s", parentCmd.command(), cmd.parent())); //
                                if (!parentCmd.command().equalsIgnoreCase(cmd.parent())) {
                                    continue;
                                }
                            }
                            finalMethod = method;
                            args = Arrays.copyOfRange(args, 1, args.length);
                            noCmd = true;
                            break;
                        }
                    }
                    if (!noCmd) break;

                }
                if (finalMethod != null) {
                    SubCommand subCommand = finalMethod.getAnnotation(SubCommand.class);
                    if (!c.permission().isEmpty() && !commandSender.hasPermission(subCommand.permission())) {
                        commandSender.sendMessage(String.format(noPerm, subCommand.permission()));
                        return true;
                    } else if (subCommand.placeholders().length > args.length) {
                        commandSender.sendMessage(String.format(noArgs, String.join(" ", subCommand.placeholders())));
                        return true;
                    } else {
                        result = (CommandResult) finalMethod.invoke(ins, commandSender, args);
                        if (result == CommandResult.SHOW_HELP) {
                            commandSender.sendMessage(methods.stream().map(m -> m.getAnnotation(SubCommand.class))
                                    .filter(sub -> sub.parent().equalsIgnoreCase(subCommand.command()))
                                    .map(sub -> "+" + sub.command() + " - " + sub.description()).toArray(String[]::new));
                            return true;
                        }
                    }
                } else {
                    commandSender.sendMessage(String.format(unknownCmd, String.join(" ", args)));
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                commandSender.sendMessage("Error: " + e.getMessage());
            }
            return result == CommandResult.RETURN_TRUE;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        for (Class<?> cmdCls : commandMap.keySet()) {
            Command c = cmdCls.getAnnotation(Command.class);
            if (!c.command().equalsIgnoreCase(command.getName())) continue;
            if (!c.permission().isEmpty() && !commandSender.hasPermission(c.permission())) {
                return null;
            }
            List<SubCommand> commands = Arrays.stream(cmdCls.getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(SubCommand.class))
                    .filter(m -> Arrays.equals(m.getParameterTypes(), new Class<?>[]{CommandSender.class, String[].class}))
                    .filter(m -> m.getReturnType() == CommandResult.class)
                    .map(m -> m.getAnnotation(SubCommand.class)).collect(Collectors.toList());

            if (strings.length == 0) {
                return commands.stream().filter(sub -> sub.parent().isEmpty()).map(SubCommand::command).collect(Collectors.toList());
            }
            String[] args = strings.clone();
            List<SubCommand> subCommands = commands;
            while (args.length != 0) {
                final String label = args[0];
                subCommands = subCommands.stream().filter(sub -> sub.parent().equalsIgnoreCase(label)).collect(Collectors.toList());
                args = Arrays.copyOfRange(args, 1, args.length);
                if (!subCommands.isEmpty()) break;
            }
            if (args.length > 0) {
                String label = args[0];
                if (subCommands.stream().anyMatch(ss -> ss.command().equals(label))) return null;
            }
            return subCommands.isEmpty() ? null : subCommands.stream().map(SubCommand::command).collect(Collectors.toList());
        }
        return null;
    }
}
