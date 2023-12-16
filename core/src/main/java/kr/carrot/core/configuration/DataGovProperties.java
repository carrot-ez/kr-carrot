package kr.carrot.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client.data-gov")
public class DataGovProperties {
    public final String baseUrl;
    public final String authKey;

    public DataGovProperties(String baseUrl, String authKey) {
        this.baseUrl = baseUrl;
        this.authKey = authKey;
    }
}
