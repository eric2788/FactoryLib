package com.ericlam.mc.factorylib.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>分支指令 | SubCommand</p>
 * <p>分支指令執行方法 | SubCommand method annotation</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubCommand {

    /**
     * 父指令 | Parent Command
     *
     * @return 父指令 | Parent Command
     */
    String parent() default "";

    /**
     * 指令 | Command
     * @return 指令 | Command
     */
    String command();

    /**
     * 指令介紹 | Command Description
     * @return 指令介紹 | Command Description
     */
    String description();

    /**
     * 權限 | Permission
     * @return 權限 | Permission
     */
    String permission() default "";

    /**
     * 參數 | args
     * @return 參數 | args
     */
    String[] placeholders() default {};
}
