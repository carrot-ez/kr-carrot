package kr.carrot.Spring.repository;

import kr.carrot.Spring.entity.SummonerSpellEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SummonerSpellRepositoryTest {

    @Autowired
    SummonerSpellRepository summonerSpellRepository;

    @Test
    @Transactional(readOnly = true)
    public void printSpell() {
        List<SummonerSpellEntity> all = summonerSpellRepository.findAll();

        all.forEach(spell -> {
            System.out.println(spell.getSpellName() + spell.getSpellKey());
        });
    }
}