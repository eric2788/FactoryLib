package com.ericlam.mc.factorylib.configuration;

/**
 * 數據獲取器 | Data Container
 *
 * @param <T> any type
 */
public interface DataGetter<T> {

    /**
     * get Data with specific file name
     *
     * @param config file name without yml | 不加 .yml 的文件名稱
     * @param path   path | 路徑
     * @return data
     */
    T getData(String config, String path);

    /**
     * get Data
     *
     * @param path path | 路徑
     * @return data
     */
    T getData(String path);

}
