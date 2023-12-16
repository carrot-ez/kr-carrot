package kr.carrot.core.configuration;

import kr.carrot.core.infra.http.client.datagov.StockClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(DataGovProperties.class)
public class DataGovConfiguration {
    @Bean
    public StockClient dataGovClient(DataGovProperties dataGovProperties, RestTemplate restTemplate) {
        return new StockClient(dataGovProperties, restTemplate);
    }
}
