package kr.carrot.Spring.repository;

import kr.carrot.Spring.entity.SummonerSpellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummonerSpellRepository extends JpaRepository<SummonerSpellEntity, Integer> {
}
