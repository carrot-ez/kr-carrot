package kr.carrot.Spring.service;

import kr.carrot.Spring.dto.ChampionMasteryDTO;
import kr.carrot.Spring.dto.SummonerDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class RiotServiceTest {

    @Autowired
    private RiotService riotService;

    @Autowired
    private Environment environment;


    @Test
    public void 소환사이름으로조회() {

        // find by name
        SummonerDTO summonerInfoByName = riotService.findSummonerInfoByName(environment.getProperty("riot.summoner-name"));

        System.out.println("summonerInfoByName = " + summonerInfoByName);
        assertThat(summonerInfoByName).isNotNull();
    }

    @Test
    public void 소환사아이디로조회() {

        // find by id
        SummonerDTO summonerInfoById = riotService.findSummonerInfoById(environment.getProperty("riot.summoner-id"));

        System.out.println("summonerInfoById = " + summonerInfoById);
        assertThat(summonerInfoById).isNotNull();
    }

    @Test
    public void 챔피언숙련도조회() {

        List<ChampionMasteryDTO> masteryList = riotService.getChampionMasteryBySummonerId(environment.getProperty("riot.summoner-id"));

        masteryList.forEach(System.out::println);
    }

}