package kr.carrot.stock.infrastructure.http.client;

import kr.carrot.core.configuration.DataGovProperties;
import kr.carrot.stock.infrastructure.http.request.StockPriceInfoRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class StockClient {
    private final String baseUrl;
    private final String authKey;
    private final RestTemplate restTemplate;

    public StockClient(DataGovProperties dataGovProperties, RestTemplate restTemplate) {
        this.baseUrl = dataGovProperties.baseUrl + dataGovProperties.path.stock();
        this.authKey = dataGovProperties.authKey;
        this.restTemplate = restTemplate;
    }

    public String getStockPriceInfo(StockPriceInfoRequest request) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/getStockPriceInfo")
                .queryParam("serviceKey", authKey)
                .queryParam("resultType", request.resultType)
                .queryParam("pageNo", request.pageNo)
                .queryParam("numOfRows", request.numOfRows)
                .build().toUri();

        return restTemplate.getForObject(uri, String.class);
    }
}
