package kr.carrot.stock.infrastructure.http.client;

import kr.carrot.core.configuration.DataGovProperties;
import org.springframework.web.client.RestTemplate;

public class StockClient {
    private final String baseUrl;
    private final String authKey;
    private final RestTemplate restTemplate;

    public StockClient(DataGovProperties dataGovProperties, RestTemplate restTemplate) {
        this.baseUrl = dataGovProperties.baseUrl + dataGovProperties.path.stock();
        this.authKey = dataGovProperties.authKey;
        this.restTemplate = restTemplate;
    }

    public String getStock() {
        return restTemplate.getForObject(
                baseUrl + "/getStockPriceInfo?serviceKey="+authKey,
                String.class
        );
    }
}
