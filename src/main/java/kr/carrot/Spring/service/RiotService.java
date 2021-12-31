package kr.carrot.Spring.service;

import kr.carrot.Spring.dto.*;
import kr.carrot.Spring.entity.KeyEntity;
import kr.carrot.Spring.exception.NotFoundException;
import kr.carrot.Spring.repository.ChampionRepository;
import kr.carrot.Spring.repository.KeyRepository;
import kr.carrot.Spring.repository.SummonerSpellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
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

    public static final String RIOT_BASE_URL = "https://kr.api.riotgames.com";
    public static final String RIOT_BASE_ASIA = "https://asia.api.riotgames.com";

    private final RestTemplate restTemplate;
    private final KeyRepository keyRepository;
    private final ChampionRepository championRepository;
    private final SummonerSpellRepository summonerSpellRepository;

    /**
     * api key 조회
     * @return
     */
    @Transactional(readOnly = true)
    public String getValidApiKey() {
        KeyEntity keyEntity = keyRepository.findFirstByOrderByCreatedAtDesc().orElseThrow(() -> new NotFoundException("api-key not found"));

        return keyEntity.getApiKey();
    }

    /**
     * api key 등록
     * @param apiKey
     * @return
     */
    public String registerApiKey(String apiKey) {
        keyRepository.deleteAll();
        KeyEntity entity = KeyEntity.builder().apiKey(apiKey).build();
        keyRepository.save(entity);

        return "success";
    }

    /**
     * 소환사 ID로 소환사 정보 조회
     *
     * @param summonerId
     * @return
     */
    @Nullable
    public SummonerDTO findSummonerInfoById(String summonerId) {

        // get api-key
        String apiKey = getValidApiKey();

        // set header
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("X-Riot-Token", apiKey);

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
     *
     * @param summonerName
     * @return
     */
    @Nullable
    public SummonerDTO findSummonerInfoByName(String summonerName) {

        // get api-key
        String apiKey = getValidApiKey();

        // set header
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("X-Riot-Token", apiKey);

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

        // get summoner info (rest call)
        ResponseEntity<SummonerDTO> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, SummonerDTO.class);

        return result.hasBody() ? result.getBody() : null;
    }

    /**
     * 챔피언 숙련도 정보 조회
     *
     * @param summonerId
     * @return
     */
    @Nullable
    public List<ChampionMasteryDTO> getChampionMasteryBySummonerId(String summonerId) {

        // get api-key
        String apiKey = getValidApiKey();

        // set header
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("X-Riot-Token", apiKey);

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
                new ParameterizedTypeReference<List<ChampionMasteryDTO>>() {
                });

        // data exists
        if (result.hasBody()) {
            List<ChampionMasteryDTO> masteryDTOS = result.getBody().stream()
                    .sorted(Comparator.comparingInt(ChampionMasteryDTO::getChampionLevel).reversed())
                    .limit(3)
                    .collect(Collectors.toList());
            return masteryDTOS;
        } else {
            return null;
        }
    }

    /**
     * 소환사 이름으로 최근 count개의 기록을 조회
     *
     * @param summonerName
     * @param count
     * @return
     */
    public List<MatchReferenceDTO> getMatchList(String summonerName, int count) {

        // get api-key
        String apiKey = getValidApiKey();

        // get account id
        SummonerDTO summonerInfo = findSummonerInfoByName(summonerName);

        // set header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Riot-Token", apiKey);

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
        if (result.hasBody() && result.getBody().getMatches() != null) {
            return result.getBody().getMatches();
        } else {
            return null;
        }
    }

    /**
     * 한 게임의 상세 정보를 조회
     *
     * @param matchId
     * @return
     */
    public MatchDto getDtlMatchInfo(String matchId) {

        // get api-key
        String apiKey = getValidApiKey();

        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Riot-Token", apiKey);

        // create Http request entity
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        // set path vars
        Map<String, String> pathVars = new HashMap<>();
        pathVars.put("matchId", matchId);

        // create uri
        URI uri = UriComponentsBuilder.fromUriString(RIOT_BASE_URL)
                .path("/lol/match/v4/matches/{matchId}")
                .buildAndExpand(matchId)
                .encode(StandardCharsets.UTF_8)
                .toUri();

        // rest call
        ResponseEntity<MatchDto> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, MatchDto.class);

        return result.hasBody() ? result.getBody() : null;
    }

    public List<String> getListOfMatchIds(String puuid) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("count", "10");
        return get("/lol/match/v5/matches/by-puuid/{puuid}/ids", new ParameterizedTypeReference<List<String>>(){ }, params, puuid);
    }

    public MatchDto getMatch(String matchId) {
        return get("/lol/match/v5/matches/{matchId}", MatchDto.class, matchId);
    }

    private <T> T get(String path, Class<T> responseType, Object ...vars) {
        HttpHeaders header = getAuthorizedHeader();

        URI uri = UriComponentsBuilder.fromUriString(RIOT_BASE_ASIA)
                .path(path)
                .buildAndExpand(vars)
                .encode(StandardCharsets.UTF_8)
                .toUri();

        RequestEntity<Object> requestEntity = new RequestEntity<>(header, HttpMethod.GET, uri);

        ResponseEntity<T> exchange = restTemplate.exchange(requestEntity, responseType); //

        return exchange.getBody();
    }

    private <T> T get(String path, ParameterizedTypeReference<T> responseType, MultiValueMap<String, String> params, Object... vars) {
        HttpHeaders header = getAuthorizedHeader();

        URI uri = UriComponentsBuilder.fromUriString(RIOT_BASE_ASIA)
                .path(path)
                .queryParams(params)
                .buildAndExpand(vars)
                .encode(StandardCharsets.UTF_8)
                .toUri();

        RequestEntity<Object> requestEntity = new RequestEntity<>(header, HttpMethod.GET, uri);

        ResponseEntity<T> exchange = restTemplate.exchange(requestEntity, responseType); //

        return exchange.getBody();
    }

    private HttpHeaders getAuthorizedHeader() {
        String apiKey = getValidApiKey();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Riot-Token", apiKey);
        return headers;
    }
}
