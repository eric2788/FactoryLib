package com.ericlam.mc.factorylib.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>指令標記 | Command Annotation</p>
 * <p>在 class 內標記 | Annotate on class</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Command {

    /**
     * 指令名稱 | Command Name
     *
     * @return 指令名稱 | Command Name
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
     * 別稱 | Alias
     * @return 別稱 | Alias
     */
    String[] alias() default {};
}
