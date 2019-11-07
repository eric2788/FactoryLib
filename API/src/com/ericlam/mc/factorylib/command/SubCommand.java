package com.ericlam.mc.factorylib.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubCommand {
    String parent() default "";

    String command();

    String description();

    String permission() default "";

    String[] placeholders() default {};

    String[] alias() default {};

    boolean showHelp() default false;
}
