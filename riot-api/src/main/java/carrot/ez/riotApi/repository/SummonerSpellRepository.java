package carrot.ez.riotApi.repository;

import carrot.ez.riotApi.entity.SummonerSpellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummonerSpellRepository extends JpaRepository<SummonerSpellEntity, Integer> {
}
