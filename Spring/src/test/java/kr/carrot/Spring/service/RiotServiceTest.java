package kr.carrot.Spring.service;

import kr.carrot.Spring.dto.SummonerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RiotServiceTest {

    @Autowired
    private RiotService riotService;

    @Test
    public void test() {
        SummonerDTO result = riotService.getSummonerInfo("hojong1351");

        System.out.println(result);
    }

}