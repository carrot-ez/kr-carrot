package kr.carrot.Spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class RiotService {

    private final RestTemplate restTemplate;
    @Value("${riot-api-key.key}")
    private String apiKey;

    public void printKey() {
        System.out.println(apiKey);
    }

    public void callApi() {
        String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/Sheria/?api_key="+apiKey;
        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);
        ResponseEntity<Map> result = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        System.out.println(result);

    }
}
