package com.ericlam.mc.factorylib.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>分支指令</p>
 * <p>分支指令執行方法</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubCommand {

    /**
     * 父指令
     *
     * @return 父指令
     */
    String parent() default "";

    /**
     * 指令
     * @return 指令
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
     * 參數
     * @return 參數
     */
    String[] placeholders() default {};
}
