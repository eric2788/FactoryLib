package com.ericlam.mc.factorylib.configuration;

/**
 * Unsupported Type Exception
 *
 * @see ConfigManager#getDataGetter(Class)
 */
public class UnSupportedTypeException extends RuntimeException {

    private Class<?> type;

    public UnSupportedTypeException(Class<?> type) {
        super("UnSupportedType when trying to get data getter: " + type.getName());
        this.type = type;
    }

    public Class<?> getUnSupportedType() {
        return type;
    }
}
