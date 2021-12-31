package kr.carrot.Spring.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.carrot.Spring.entity.ChampionEntity;
import kr.carrot.Spring.entity.SummonerSpellEntity;
import kr.carrot.Spring.repository.ChampionRepository;
import kr.carrot.Spring.repository.SummonerSpellRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
class JsonUtilTest {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ChampionRepository championRepository;

    @Autowired
    SummonerSpellRepository summonerSpellRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void 챔피언데이터입력() {
        try {
            HashMap<?, ?> json = JsonUtil.readJson(HashMap.class, "champion.json");

            LinkedHashMap<?, ?> data = (LinkedHashMap<?, ?>) json.get("data");

            for (Map.Entry<?, ?> entry : data.entrySet()) {

                String championName = (String) entry.getKey();

                LinkedHashMap<?, ?> value = (LinkedHashMap<?, ?>) entry.getValue();
                int key = Integer.parseInt( (String) value.get("key"));

                ChampionEntity entity = ChampionEntity.builder()
                        .key(key)
                        .name(championName)
                        .build();

                championRepository.save(entity);
            }
        }
        catch(IOException ie) {
            System.out.println(ie.getMessage());
        }

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 서머너스펠_디비_입력() {
        try {
            HashMap<?, ?> json = JsonUtil.readJson(HashMap.class, "summoner.json");

            LinkedHashMap<?, ?> data = (LinkedHashMap<?, ?>) json.get("data");

            for (Map.Entry<?, ?> entry : data.entrySet()) {
                String spellName = (String) entry.getKey();
                HashMap<?, ?> value = (HashMap<?, ?>) entry.getValue();

                int key = Integer.parseInt((String) value.get("key"));

                SummonerSpellEntity entity = SummonerSpellEntity.builder()
                        .spellKey(key)
                        .spellName(spellName)
                        .build();

                summonerSpellRepository.save(entity);

                System.out.println("complete: " + spellName);
            }
        }
        catch(IOException ie) {
            System.out.println(ie.getMessage());
        }
    }
}