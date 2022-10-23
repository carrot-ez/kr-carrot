package carrot.ez.riotApi.repository;

import carrot.ez.riotApi.entity.ChampionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<ChampionEntity, Integer> {
}
