package kr.carrot.Spring.service;

import kr.carrot.Spring.dto.*;
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
        SummonerDto summonerInfoByName = riotService.findSummonerInfoByName(summonerName).orElse(null);
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
        SummonerDto summonerDTO = riotService.findSummonerInfoByName("Sheria").orElse(null);

        // when
        List<String> listOfMatchIds = riotService.getMatchIdListByPuuid(summonerDTO.getPuuid());

        // then
        assertThat(listOfMatchIds.size()).isEqualTo(10);
    }

    @Test
    public void getMatchData() {
        // given
        SummonerDto summonerDTO = riotService.findSummonerInfoByName("Sheria").orElse(null);
        String matchId = riotService.getMatchIdListByPuuid(summonerDTO.getPuuid()).get(0);

        // when
        MatchDto match = riotService.getMatch(matchId);

        // then
        assertThat(match).isNotNull();
        match.getInfo().getParticipants().forEach(System.out::println);
    }

    @Test
    public void getHistory() {
        // given
        String summonerName = "Sheria";

        // when
        SummonerHistory history = riotService.getHistory(summonerName);

        // then
        assertThat(history.getGameInfos().size()).isEqualTo(10);
        history.getGameInfos().forEach(System.out::println);
    }
}