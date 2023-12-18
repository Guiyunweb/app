package plus.guiyun.app.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "auth.token")
public class TokenConfig {


    public static String header;

    public static String secret;

    public static Long expireTime;

    public final static String client = "client";

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        TokenConfig.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        TokenConfig.secret = secret;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        TokenConfig.expireTime = expireTime;
    }
}

