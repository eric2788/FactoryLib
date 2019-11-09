package com.ericlam.mc.factorylib.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>指令標記</p>
 * <p>在 class 內標記</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Command {

    /**
     * 指令名稱
     *
     * @return 指令名稱
     */
    String command();

    /**
     * 指令介紹
     * @return 指令介紹
     */
    String description();

    /**
     * 權限
     * @return 權限
     */
    String permission() default "";

    /**
     * 別稱
     * @return 別稱
     */
    String[] alias() default {};
}
