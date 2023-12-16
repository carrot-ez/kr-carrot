package kr.carrot.core.infra.http.client.datagov;

import kr.carrot.core.configuration.DataGovProperties;
import org.springframework.web.client.RestTemplate;

public class StockClient {
    private final String baseUrl;
    private final String authKey;
    private final RestTemplate restTemplate;

    public StockClient(DataGovProperties dataGovProperties, RestTemplate restTemplate) {
        this.baseUrl = dataGovProperties.baseUrl + "/1160100/service/GetStockSecuritiesInfoService";
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
