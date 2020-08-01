package com.king.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取配置文件
 */
@Slf4j
public class DynamicDataUtils {
    /**
     * 保存后台所有商户访问域名及数据库映射关系
     */
    private static Map<String, String> databases = new ConcurrentHashMap<>();
    /**
     * 保存后台所有商户访问域名及数据库类型映射关系
     */
    private static Map<String, String> dbTypes = new ConcurrentHashMap<>();
    /**
     * 保存后台所有商户访问域名及数据源映射关系
     */
    private static Map<String, String> datasources = new ConcurrentHashMap<>();

    //读取databases.properties配置文件
    public static Properties getProperties() throws IOException {
        String serverAddr = "192.168.177.129:8848";
        String dataId = "databases.properties";
        String group = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        ConfigService configService = null;
        Properties proper = new Properties();
        try {
            configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            proper.load(new StringReader(content));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proper;
    }

    /**
     * 初始化数据库源配置
     */
    public static void init() {
        try {
            Properties properties = getProperties();
            Set<String> domains = properties.stringPropertyNames();
            for (String domain : domains) {
                if (domain.contains(".dbtype")) {
                    dbTypes.put(domain.replace(".dbtype", ""), properties.getProperty(domain));
                } else if (domain.contains(".database")) {
                    databases.put(domain.replace(".database", ""), properties.getProperty(domain));
                } else {
                    datasources.put(domain, properties.getProperty(domain));
                }
            }
        } catch (Exception e) {
            log.error("databases.properties文件初始读取错误", e);
        }
    }

}
