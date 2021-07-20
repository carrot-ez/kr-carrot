package kr.carrot.Spring.repository;

import kr.carrot.Spring.entity.KeyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KeyRepositoryTest {

    @Autowired
    KeyRepository repository;
    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    public void DB_SAVE_테스트() {
        KeyEntity entity = KeyEntity.builder().apiKey("1").build();

        repository.save(entity);
        em.flush();
        em.clear();

        KeyEntity findEntity = repository.findById("1").get();

        assertThat(findEntity.getApiKey()).isEqualTo("1");
    }

    @Test
    @Transactional
    public void DB_FIND_테스트() {

        KeyEntity entity = KeyEntity.builder().apiKey("1").build();

        repository.save(entity);
        em.flush();
        em.clear();

        KeyEntity entity2 = KeyEntity.builder().apiKey("2").build();

        repository.save(entity2);
        em.flush();
        em.clear();

//        KeyEntity keyEntity = repository.findAllOrderByCreatedAtDesc().get(0);
        KeyEntity keyEntity = repository.findFirstByOrderByCreatedAtDesc().get();
        assertThat(keyEntity.getApiKey()).isEqualTo("2");
    }
}