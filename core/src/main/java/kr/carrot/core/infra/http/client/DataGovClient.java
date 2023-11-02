package kr.carrot.core.infra.http.client;

import kr.carrot.core.configuration.DataGovProperties;
import org.springframework.web.client.RestTemplate;

public class DataGovClient {
    private final DataGovProperties dataGovProperties;
    private final RestTemplate restTemplate;

    public DataGovClient(DataGovProperties dataGovProperties, RestTemplate restTemplate) {
        this.dataGovProperties = dataGovProperties;
        this.restTemplate = restTemplate;
    }

    public Object getStock() {
        return restTemplate.getForObject(
                dataGovProperties.baseUrl + "1160100/service/GetStockSecuritiesInfoService",
                Object.class
        );
    }
}
