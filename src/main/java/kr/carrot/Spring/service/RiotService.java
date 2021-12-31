package kr.carrot.Spring.service;

import kr.carrot.Spring.dto.*;
import kr.carrot.Spring.dto.res.InGamePlayerInfo;
import kr.carrot.Spring.dto.res.SummonerHistory;
import kr.carrot.Spring.entity.ChampionEntity;
import kr.carrot.Spring.entity.KeyEntity;
import kr.carrot.Spring.entity.SummonerSpellEntity;
import kr.carrot.Spring.exception.InvalidDataException;
import kr.carrot.Spring.exception.NotFoundException;
import kr.carrot.Spring.repository.ChampionRepository;
import kr.carrot.Spring.repository.KeyRepository;
import kr.carrot.Spring.repository.SummonerSpellRepository;
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
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RiotService {

    private final RestTemplate restTemplate;
    private final KeyRepository keyRepository;
    private final ChampionRepository championRepository;
    private final SummonerSpellRepository summonerSpellRepository;

    public static final String RIOT_BASE_URL = "https://kr.api.riotgames.com";

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
        System.out.println("apiKey = " + apiKey);

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
    public MatchDTO getDtlMatchInfo(String matchId) {

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
        ResponseEntity<MatchDTO> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, MatchDTO.class);

        return result.hasBody() ? result.getBody() : null;
    }


    /**
     * 게임 내 플레이어의 상세정보 조회
     *
     * @param matchId
     * @param summonerName
     * @return
     */
    public InGamePlayerInfo getInGamePlayerInfo(String matchId, String summonerName) {

        // get match info
        MatchDTO dtlMatchInfo = getDtlMatchInfo(matchId);

        ParticipantIdentityDTO participantIdentityDTO = dtlMatchInfo.getParticipantIdentities()
                .stream()
                .filter(e -> e.getPlayer().getSummonerName().equals(summonerName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("participant identity not found"));

        InGamePlayerInfo player = dtlMatchInfo.getParticipants().stream()
                .filter(e -> e.getParticipantId() == participantIdentityDTO.getParticipantId())
                .map(e -> {

                    ChampionEntity championEntity = championRepository.findById(e.getChampionId())
                            .orElseThrow(() -> new InvalidDataException("invalid champion id"));

                    SummonerSpellEntity spell1Entity = summonerSpellRepository.findById(e.getSpell1Id())
                            .orElseThrow(() -> new InvalidDataException("invalid spell 1 id"));

                    SummonerSpellEntity spell2Entity = summonerSpellRepository.findById(e.getSpell2Id())
                            .orElseThrow(() -> new InvalidDataException("invalid spell 2 id"));

                    InGamePlayerInfo inGamePlayerInfo = InGamePlayerInfo.builder()
                            .summonerName(summonerName)
                            .highestAchievedSeasonTier(e.getHighestAchievedSeasonTier())
                            .assists(e.getStats().getAssists())
                            .champion(championEntity.getName())
                            .deaths(e.getStats().getDeaths())
                            .goldEarned(e.getStats().getGoldEarned())
                            .item0(e.getStats().getItem0())
                            .item1(e.getStats().getItem1())
                            .item2(e.getStats().getItem2())
                            .item3(e.getStats().getItem3())
                            .item4(e.getStats().getItem4())
                            .item5(e.getStats().getItem5())
                            .item6(e.getStats().getItem6())
                            .kills(e.getStats().getKills())
                            .spell1(spell1Entity.getSpellName())
                            .spell2(spell2Entity.getSpellName())
                            .win(e.getStats().isWin())
                            .build();

                    return inGamePlayerInfo;
                })
                .findFirst()
                .orElseThrow(() -> new NotFoundException("participant not found"));

        return player;
    }

    /**
     * count 게임간의 게임 이력 조회
     * @param summonerName
     * @param count
     * @return
     */
    public SummonerHistory getSummonerHistory(String summonerName, int count) {

        // get summoner info
        SummonerDTO summonerDTO = findSummonerInfoByName(summonerName);
        if (summonerDTO == null) {
            throw new NotFoundException("summoner info not found");
        }

        // get match refeerence
        List<MatchReferenceDTO> matchRefList = getMatchList(summonerName, count);

        int win = 0;
        int lose = 0;
        List<InGamePlayerInfo> inGamePlayerInfos = new ArrayList<>();

        for (MatchReferenceDTO e : matchRefList) {
            // add in game info
            InGamePlayerInfo inGamePlayerInfo = getInGamePlayerInfo(e.getGameId(), summonerName);
            inGamePlayerInfos.add(inGamePlayerInfo);

            // count win
            if (inGamePlayerInfo.isWin()) {
                win += 1;
            } else {
                lose += 1;
            }
        }// for

        // set result dto
        SummonerHistory history = SummonerHistory.builder()
                .summonerName(summonerName)
                .summonerLevel(summonerDTO.getSummonerLevel())
                .profileIcon(summonerDTO.getProfileIconId())
                .win(win)
                .lose(lose)
                .inGamePlayerInfos(inGamePlayerInfos)
                .build();

        return history;
    }
}
