package com.ericlam.mc.factorylib.configuration;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.function.Function;

/**
 * Config Manager
 */
public interface ConfigManager {

    /**
     * register config
     *
     * @param config file name without yml | 不加 .yml 的文件名稱
     */
    void registerConfig(String... config);

    /**
     * register msg config
     *
     * @param msgConfig file name without yml | 不加 .yml 的文件名稱
     */
    void registerMessage(String... msgConfig);

    /**
     * get Data Getter
     *
     * @param type data type | 數據類型
     * @param <T>  type
     * @return data getter wtih that type | 數據獲取器
     * @throws UnSupportedTypeException cannot find that type, you may need to register by using {@link ConfigManager#registerTypeGetter(Class, Function)}
     */
    <T> DataGetter<T> getDataGetter(Class<T> type) throws UnSupportedTypeException;

    /**
     * regsiter type getter
     *
     * @param type   the type you register
     * @param getter getter function
     * @param <T>    type
     */
    <T> void registerTypeGetter(Class<T> type, Function<Object, T> getter);

    /**
     * get message | 獲取訊息
     *
     * @param path path | 路徑
     * @return 訊息 | message
     */
    String getMessage(String path);

    /**
     * 獲取訊息列表 | get message list
     *
     * @param path path | 路徑
     * @return message list
     */
    List<String> getMessageList(String path);

    /**
     * get config
     *
     * @param config file name without yml | 不加 .yml 的文件名稱
     * @return config
     */
    FileConfiguration getConfig(String config);

}
