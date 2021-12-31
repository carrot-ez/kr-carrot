package kr.carrot.Spring.repository;

import kr.carrot.Spring.entity.ChampionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<ChampionEntity, Integer> {
}
