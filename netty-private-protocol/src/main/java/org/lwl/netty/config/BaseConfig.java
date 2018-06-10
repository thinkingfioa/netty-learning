package org.lwl.netty.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author thinking_fioa
 * @createTime 2018/5/15
 * @description
 */


public class BaseConfig {
    private static final Logger LOGGER = LogManager.getLogger(BaseConfig.class);

    public static String getStringProperty(Map<String, String> props, String key, String defaultVal) {
        Object value = props.get(key);
        if(null == value) {
            return defaultVal;
        }

        return String.valueOf(props.get(key)).trim();
    }

    public static int getIntProperty(Map<String, String> props, String key, int defaultVal) {
        Object value = props.get(key);
        if(null == value) {
            return defaultVal;
        }

        return Integer.parseInt(props.get(key).trim());
    }

    public static long getLongProperty(Map<String, String> props, String key, long defaultVal) {
        Object value = props.get(key);
        if(null == value) {
            return defaultVal;
        }

        return Long.parseLong(props.get(key).trim());
    }

    public static byte getByteProperty(Map<String, String> props, String key, byte defaultVal) {
        Object value = props.get(key);
        if(null == value) {
            return defaultVal;
        }

        return Byte.parseByte(props.get(key).trim());
    }

    public static boolean getBooleanProperty(Map<String, String> props, String key, Boolean defaultVal) {
        Object value = props.get(key);
        if(null == value) {
            return defaultVal;
        }

        return Boolean.parseBoolean(props.get(key).trim());
    }

    public static Map<String, String> getConfigSettings(Properties pros) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        for (Map.Entry<Object, Object> entry : pros.entrySet()) {
            String key = String.valueOf(entry.getKey()).trim();
            String value = String.valueOf(pros.get(key));
            map.put(key, value);
        }

        return map;
    }
}
