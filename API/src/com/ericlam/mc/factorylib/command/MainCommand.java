package com.ericlam.mc.factorylib.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MainCommand {
    String[] placeholders() default {};

    boolean showHelp() default false;
}
