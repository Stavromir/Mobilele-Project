package bg.softuni.mobilele.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "google.recaptcha")
public class ReCaptchaConfig {

    private String key;
    private String secret;

    public String getKey() {
        return key;
    }

    public ReCaptchaConfig setKey(String key) {
        this.key = key;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public ReCaptchaConfig setSecret(String secret) {
        this.secret = secret;
        return this;
    }
}
