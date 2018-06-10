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
    /**
     * 传输消息最大字节数，缺省 4k
     */
    private static int pkgMaxLen = 1024 << 2;

    /**
     * Client 连接的Ip
     */
    private static String ip = "127.0.0.1";

    /**
     * Server 监听端口
     */
    private static int port= 9080;

    /**
     * 心跳间隔时间，包括读写空闲，unit: s
     */
    private static int heartbeatInterval = 5;

    private static String userName = "thinkingfioa";
    private static String password = "123456";

    /**
     * Netty消息编码格式。 1 - Marshalling; 2 - Kryo; 3 - Protobuf; 4 - Thrift; 5 - Avro
     */
    private static byte codecType = 1;

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
        heartbeatInterval = BaseConfig.getIntProperty(propertiesMap, "heartbeat.interval", heartbeatInterval);
        userName = BaseConfig.getStringProperty(propertiesMap, "username", userName);
        password = BaseConfig.getStringProperty(propertiesMap, "password", password);
        pkgMaxLen = BaseConfig.getIntProperty(propertiesMap, "pkg.max.len", pkgMaxLen);
        port = BaseConfig.getIntProperty(propertiesMap, "port", port);
        ip = BaseConfig.getStringProperty(propertiesMap, "ip", ip);
        codecType = BaseConfig.getByteProperty(propertiesMap, "message.codec", codecType);
    }

    public static String getCharsetFormat() {
        return charsetFormat;
    }

    public static int getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static int getPkgMaxLen() {
        return pkgMaxLen;
    }

    public static int getPort() {
        return port;
    }

    public static String getIp() {
        return ip;
    }

    public static byte getCodecType() {
        return codecType;
    }
}
