package kr.carrot.stock.infrastructure.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.carrot.core.configuration.DataGovProperties;
import kr.carrot.core.infrastructure.http.client.HttpClient;
import kr.carrot.stock.infrastructure.http.request.StockPriceInfoRequest;
import kr.carrot.stock.infrastructure.http.response.StockApiResponse;
import kr.carrot.stock.infrastructure.http.response.StockPriceInfoItemResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

public class StockClient {
    private final String baseUrl;
    private final String authKey;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public StockClient(DataGovProperties dataGovProperties, HttpClient httpClient, ObjectMapper objectMapper) {
        this.baseUrl = dataGovProperties.baseUrl + dataGovProperties.path.stock();
        this.authKey = dataGovProperties.authKey;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public Object getStockPriceInfo(StockPriceInfoRequest request) throws JsonProcessingException {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/getStockPriceInfo")
                .queryParam("serviceKey", authKey)
                .queryParam("resultType", request.resultType)
                .queryParam("pageNo", request.pageNo)
                .queryParam("numOfRows", request.numOfRows)
                .encode(StandardCharsets.UTF_8)
                .build().toUri();

        String result = httpClient.get(uri, String.class).getBody();
        return objectMapper.readValue(result, new TypeReference<StockApiResponse<StockPriceInfoItemResponse>>() {});
    }
}
