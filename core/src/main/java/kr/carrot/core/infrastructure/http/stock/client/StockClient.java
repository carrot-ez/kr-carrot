package kr.carrot.core.infrastructure.http.stock.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.carrot.core.configuration.DataGovProperties;
import kr.carrot.core.domain.common.PageContent;
import kr.carrot.core.infrastructure.http.client.HttpClient;
import kr.carrot.core.infrastructure.http.stock.request.StockPriceInfoRequest;
import kr.carrot.core.infrastructure.http.stock.request.ResultType;
import kr.carrot.core.infrastructure.http.stock.response.StockApiResponse;
import kr.carrot.core.infrastructure.http.stock.response.StockPriceInfoItemResponse;
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

    public PageContent<StockPriceInfoItemResponse> getStockPriceInfo(StockPriceInfoRequest request) throws JsonProcessingException {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/getStockPriceInfo")
                .queryParam("serviceKey", authKey)
                .queryParam("resultType", ResultType.json)
                .queryParam("pageNo", request.pageNo())
                .queryParam("numOfRows", request.numOfRows())
                .queryParam("basDt", request.basDt())
                .queryParam("beginBasDt", request.beginBasDt())
                .queryParam("endBasDt", request.endBasDt())
                .queryParam("likeBasDt", request.likeBasDt())
                .queryParam("mrktCls", request.mrktCls())
                .queryParam("beginVs", request.beginVs())
                .queryParam("endVs", request.endVs())
                .queryParam("beginFltRt", request.beginFltRt())
                .queryParam("endFltRt", request.endFltRt())
                .queryParam("beginTrqu", request.beginTrqu())
                .queryParam("endTrqu", request.endTrqu())
                .queryParam("beginTrPrc", request.beginTrPrc())
                .queryParam("endTrPrc", request.endTrPrc())
                .queryParam("beginLstgStCnt", request.beginLstgStCnt())
                .queryParam("endLstgStCnt", request.endLstgStCnt())
                .queryParam("beginMrktTotAmt", request.beginMrktTotAmt())
                .queryParam("endMrktTotAmt", request.endMrktTotAmt())
                .encode(StandardCharsets.UTF_8)
                .build().toUri();

        String result = httpClient.get(uri, String.class).getBody();
        StockApiResponse<StockPriceInfoItemResponse> response = objectMapper.readValue(result, new TypeReference<>() {});
        StockApiResponse.Body<StockPriceInfoItemResponse> body = response.response().body();
        return new PageContent<>(body.pageNo(), body.numOfRows(), body.totalCount(), body.items().item());
    }
}
