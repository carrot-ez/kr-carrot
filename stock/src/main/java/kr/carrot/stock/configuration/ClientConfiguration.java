package kr.carrot.stock.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.carrot.core.configuration.DataGovConfiguration;
import kr.carrot.core.configuration.DataGovProperties;
import kr.carrot.core.configuration.HttpClientConfiguration;
import kr.carrot.core.infrastructure.http.client.HttpClient;
import kr.carrot.core.infrastructure.http.stock.client.StockClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataGovConfiguration.class, HttpClientConfiguration.class})
public class ClientConfiguration {
    @Bean
    public StockClient stockClient(DataGovProperties dataGovProperties, HttpClient httpClient, ObjectMapper objectMapper) {
        return new StockClient(dataGovProperties, httpClient, objectMapper);
    }
}
