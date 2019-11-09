package com.ericlam.mc.factorylib.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>主指令</p>
 * <p>沒有任何參數時執行的方法</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MainCommand {
    /**
     * 參數
     *
     * @return 參數
     */
    String[] placeholders() default {};
}
