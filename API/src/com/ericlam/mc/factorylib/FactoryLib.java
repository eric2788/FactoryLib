package com.ericlam.mc.factorylib;

public final class FactoryLib implements FactoryAPI {

    public static FactoryAPI getApi() {
        return null;
    }

    @Override
    public InventoryFactory getInventoryFactory() {
        return null;
    }

    @Override
    public ItemStackFactory getItemStackFactory() {
        return null;
    }

    @Override
    public CommandManager getCommandManager() {
        return null;
    }
}
