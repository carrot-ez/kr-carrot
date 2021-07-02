package kr.carrot.Spring.service;

import kr.carrot.Spring.dto.ChampionMasteryDTO;
import kr.carrot.Spring.dto.MatchReferenceDTO;
import kr.carrot.Spring.dto.MatchListDTO;
import kr.carrot.Spring.dto.SummonerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RiotService {

    private final RestTemplate restTemplate;

    @Value("${riot.api-key}")
    private String API_KEY;

    @Value("${riot.summoner-id}")
    private String SUMMONER_ID_ATRON;

    public static final String RIOT_BASE_URL = "https://kr.api.riotgames.com";

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
                .encode(StandardCharsets.UTF_8) // 한글이 그대로 들어가면 에러남
                .toUri();

        System.out.println(uri.toString());

        // get summoner info (rest call)
        ResponseEntity<SummonerDTO> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, SummonerDTO.class);

        return result.hasBody() ? result.getBody() : null;
    }

    /**
     * 챔피언 숙련도 정보 조회
     * @param summonerId
     * @return
     */
    @Nullable
    public List<ChampionMasteryDTO> getChampionMasteryBySummonerId(String summonerId) {

        // set header
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("X-Riot-Token", API_KEY);

        // create request entity
        HttpEntity<?> requestEntity = new HttpEntity<>(header);

        // pathVariable
        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("encryptedSummonerId", summonerId);

        // create uri
        URI uri = UriComponentsBuilder //
                .fromUriString(RIOT_BASE_URL)
                .path("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}")
                .buildAndExpand(pathVariable)
                .toUri();

        // get champion mastery (rest call)
        ResponseEntity<List<ChampionMasteryDTO>> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
                new ParameterizedTypeReference<List<ChampionMasteryDTO>>() { });

        // data exists
        if( result.hasBody() ) {
            List<ChampionMasteryDTO> masteryDTOS = result.getBody().stream()
                    .sorted(Comparator.comparingInt(ChampionMasteryDTO::getChampionLevel).reversed())
                    .limit(3)
                    .collect(Collectors.toList());
            return masteryDTOS;
        }
        else {
            return null;
        }
    }

    /**
     * 소환사 이름으로 최근 count개의 기록을 조회
     * @param summonerName
     * @param count
     * @return
     */
    public List<MatchReferenceDTO> getMatchList(String summonerName, int count) {

        // get account id
        SummonerDTO summonerInfo = findSummonerInfoByName(summonerName);

        // set header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Riot-Token", API_KEY);

        // create http request entity
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        // set pathVariables
        Map<String, String> pathVars = new HashMap<>();
        pathVars.put("encryptedAccountId", summonerInfo.getAccountId());

        // build uri
        URI uri = UriComponentsBuilder.fromUriString(RIOT_BASE_URL)
                .path("/lol/match/v4/matchlists/by-account/{encryptedAccountId}")
                .queryParam("beginIndex", 0)
                .queryParam("endIndex", count)
                .buildAndExpand(pathVars)
                .encode(StandardCharsets.UTF_8)
                .toUri();

        // get math list
        ResponseEntity<MatchListDTO> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, MatchListDTO.class);

        // return match list
        if(result.hasBody() && result.getBody().getMatches() != null) {
            return result.getBody().getMatches();
        }
        else {
            return null;
        }

    }

}
