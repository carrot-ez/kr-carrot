package kr.carrot.Spring.service;

import kr.carrot.Spring.dto.*;
import kr.carrot.Spring.dto.res.InGamePlayerInfo;
import kr.carrot.Spring.dto.res.SummonerHistory;
import kr.carrot.Spring.repository.KeyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class RiotServiceTest {

    @Autowired
    private RiotService riotService;
    @Autowired
    private KeyRepository keyRepository;

    private final String summonerName = "아트런";
    private final String summonerId = "RdusDM7DKdcLYvb7HxrUyA6qm6xHWV29psxHjcfki3uFpzI";

    @Test
    public void 소환사이름으로조회() {

        // find by name
        SummonerDTO summonerInfoByName = riotService.findSummonerInfoByName(summonerName);
//        SummonerDTO summonerInfoByName = riotService.findSummonerInfoByName("sheria");

        System.out.println("summonerInfoByName = " + summonerInfoByName);
        assertThat(summonerInfoByName).isNotNull();
    }

    @Test
    public void 챔피언숙련도조회() {

        List<ChampionMasteryDTO> masteryList = riotService.getChampionMasteryBySummonerId(summonerId);

        assertThat(masteryList).isNotNull();
        masteryList.forEach(System.out::println);
    }

    @Test
    public void 최근게임이력조회() {

        // 최근 5건의 경기 조회
        List<MatchReferenceDTO> matchList = riotService.getMatchList(summonerName, 5);

        assertThat(matchList).isNotNull();
        matchList.forEach(System.out::println);
    }

    @Test
    public void 상세게임정보조회() {

        // 최근 1게임 조회
        List<MatchReferenceDTO> matchList = riotService.getMatchList(summonerName, 1);

        // 상세정보 조회
        MatchDto matchInfo = riotService.getDtlMatchInfo(matchList.get(0).getGameId());

        // null check
        assertThat(matchInfo).isNotNull();

        // print game info
        System.out.println("matchInfo = " + matchInfo);
    }

    @Test
    @Transactional
    public void apiKey등록() {

        String validApiKey = riotService.getValidApiKey();
        System.out.println("validApiKey = " + validApiKey);

        // given
        riotService.registerApiKey("1234");
        riotService.registerApiKey("9999");

        // when
        int size = keyRepository.findAll().size();

        // then
        assertThat(size).isEqualTo(1);
    }

    @Test
    public void getMatchIdList() {
        // given
        SummonerDTO summonerDTO = riotService.findSummonerInfoByName("Sheria");

        // when
        List<String> listOfMatchIds = riotService.getListOfMatchIds(summonerDTO.getPuuid());

        // then
        assertThat(listOfMatchIds.size()).isEqualTo(10);
    }

    @Test
    public void getMatchData() {
        // given
        SummonerDTO summonerDTO = riotService.findSummonerInfoByName("Sheria");
        String matchId = riotService.getListOfMatchIds(summonerDTO.getPuuid()).get(0);

        // when
        MatchDto match = riotService.getMatch(matchId);

        // then
        assertThat(match).isNotNull();
        match.getInfo().getParticipants().forEach(System.out::println);
    }
}