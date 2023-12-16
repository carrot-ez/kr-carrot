package kr.carrot.core.configuration;

import kr.carrot.core.infrastructure.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpClient httpClient(RestTemplate restTemplate) {
        return new HttpClient(restTemplate);
    }
}
