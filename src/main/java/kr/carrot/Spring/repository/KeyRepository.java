package kr.carrot.Spring.repository;

import kr.carrot.Spring.entity.KeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeyRepository extends JpaRepository<KeyEntity, String> {


    // 굳이 이렇게 쓸 필요 X
    @Deprecated
    @Query("select entity " +
            "from KeyEntity entity " +
            "order by entity.createdAt desc")
    List<KeyEntity> findAllOrderByCreatedAtDesc();


    Optional<KeyEntity> findFirstByOrderByCreatedAtDesc();
}
