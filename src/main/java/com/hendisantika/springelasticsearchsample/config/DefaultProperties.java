package com.hendisantika.springelasticsearchsample.config;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-elasticsearch-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/06/24
 * Time: 22.53
 * To change this template use File | Settings | File Templates.
 */
public class DefaultProperties {
    private DefaultProperties() {
    }

    public static Properties getDefaultProperties() {
        Properties p = new Properties();
        p.setProperty("server.address", "0.0.0.0");
        p.setProperty("server.port", "8080");
        p.setProperty("server.scheme", "http");
        p.setProperty("server.servlet.context-path", "/");
        p.setProperty("spring.output.ansi.enabled", "ALWAYS");
        return p;
    }
}
