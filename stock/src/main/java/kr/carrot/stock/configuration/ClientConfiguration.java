package kr.carrot.stock.configuration;

import kr.carrot.core.configuration.DataGovConfiguration;
import kr.carrot.core.configuration.DataGovProperties;
import kr.carrot.core.configuration.RestTemplateConfiguration;
import kr.carrot.stock.infrastructure.http.client.StockClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({DataGovConfiguration.class, RestTemplateConfiguration.class})
public class ClientConfiguration {
    @Bean
    public StockClient stockClient(DataGovProperties dataGovProperties, RestTemplate restTemplate) {
        return new StockClient(dataGovProperties, restTemplate);
    }
}
