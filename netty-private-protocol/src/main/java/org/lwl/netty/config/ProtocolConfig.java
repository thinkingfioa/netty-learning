package org.lwl.netty.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author thinking_fioa
 * @createTime 2018/5/15
 * @description 配置文件读取
 */


public class ProtocolConfig {
    private static final Logger LOGGER = LogManager.getLogger(ProtocolConfig.class);

    /**
     * 配置文件名字
     */
    private static final String PROTOCOL_PROPERTIES = "netty-private-protocol.properties";

    private static  String charsetFormat="UTF-8";

    public static void init() throws IOException {
        LOGGER.error("Reading configuration: {}", PROTOCOL_PROPERTIES);
        InputStream inputStream = ProtocolConfig.class.getClassLoader().getResourceAsStream(PROTOCOL_PROPERTIES);
        Properties props = new Properties();
        try {
            props.load(inputStream);
        } finally {
            if(null != inputStream) {
                inputStream.close();
            }
        }

        Map<String, String> propertiesMap = BaseConfig.getConfigSettings(props);
        charsetFormat = BaseConfig.getStringProperty(propertiesMap, "charset.format", charsetFormat);
    }

    public static String getCharsetFormat() {
        return charsetFormat;
    }
}
