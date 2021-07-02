package kr.carrot.Spring.service;

import kr.carrot.Spring.dto.SummonerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class RiotService {

    private final RestTemplate restTemplate;
    @Value("${riot.api-key}")
    private static String API_KEY;

    @Value("${riot.summoner-id}")
    private static String SUMMONER_ID_ATRON;

    private static final String RIOT_BASE_URL = "https://kr.api.riotgames.com";

    /**
     * 소환사 ID로 소환사 정보 조회
     * @param summonerId
     * @return
     */
    @Nullable
    public SummonerDTO findSummonerInfoById(String summonerId) {

        // set header
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("X-Riot-Token", API_KEY);

        // create request entity
        HttpEntity<?> requestEntity = new HttpEntity<>(header);

        // create url params
        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("encryptedSummonerId", summonerId);

        // create uri
        URI uri = UriComponentsBuilder //
                .fromUriString(RIOT_BASE_URL)
                .path("/lol/summoner/v4/summoners/{encryptedSummonerId}")
                .buildAndExpand(pathVariable)
                .toUri();

        // get summoner info (rest call)
        ResponseEntity<SummonerDTO> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, SummonerDTO.class);

        return result.hasBody() ? result.getBody() : null;
    }

    /**
     * 소환사 명으로 소환사 정보 조회
     * @param summonerName
     * @return
     */
    @Nullable
    public SummonerDTO findSummonerInfoByName(String summonerName) {

        // set header
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("X-Riot-Token", API_KEY);

        // create request entity
        HttpEntity<?> requestEntity = new HttpEntity<>(header);

        // create url params
        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("summonerName", summonerName);

        // create uri
        URI uri = UriComponentsBuilder //
                .fromUriString(RIOT_BASE_URL)
                .path("/lol/summoner/v4/summoners/by-name/{summonerName}")
                .buildAndExpand(pathVariable)
                .toUri();

        // get summoner info (rest call)
        ResponseEntity<SummonerDTO> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, SummonerDTO.class);

        return result.hasBody() ? result.getBody() : null;
    }

}
