package kr.carrot.core.configuration;

import kr.carrot.core.infra.http.client.DataGovClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(DataGovProperties.class)
public class DataGovConfiguration {
    @Bean
    public DataGovClient dataGovClient(DataGovProperties dataGovProperties, RestTemplate restTemplate) {
        return new DataGovClient(dataGovProperties, restTemplate);
    }
}
