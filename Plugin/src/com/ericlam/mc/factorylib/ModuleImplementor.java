package com.ericlam.mc.factorylib;

import com.ericlam.mc.factorylib.factories.InventoryBuilder;
import com.ericlam.mc.factorylib.factories.ItemStackBuilder;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

import java.util.HashMap;
import java.util.Map;

public final class ModuleImplementor implements Module {

    private Map<Class, Object> instanceMap = new HashMap<>();
    private Map<Class<?>, Class> classMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public void configure(Binder binder) {
        binder.bind(InventoryFactory.class).to(InventoryBuilder.class).in(Scopes.NO_SCOPE);
        binder.bind(ItemStackFactory.class).to(ItemStackBuilder.class).in(Scopes.NO_SCOPE);
        binder.bind(CommandManager.class).to(CommandHandler.class).in(Scopes.SINGLETON);

        classMap.forEach((cls, c) -> binder.bind(cls).to(c));
        instanceMap.forEach((cls, obj) -> binder.bind(cls).toInstance(obj));
    }

    public <E, T extends E> void bind(Class<E> cls, T obj) {
        this.instanceMap.put(cls, obj);
    }

    public <E, T extends E> void bind(Class<E> cls, Class<T> c) {
        this.classMap.put(cls, c);
    }
}
