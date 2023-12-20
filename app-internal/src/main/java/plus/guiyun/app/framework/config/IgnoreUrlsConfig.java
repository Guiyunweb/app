package plus.guiyun.app.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 匿名url配置
 * Created by myy on 2021/8/26.
 */

@ConfigurationProperties(prefix = "auth.ignored")
@Configuration
public class IgnoreUrlsConfig {

    // 匿名url
    public static String[] urls = {};


    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[]urls) {
        IgnoreUrlsConfig.urls = urls;
    }
}
