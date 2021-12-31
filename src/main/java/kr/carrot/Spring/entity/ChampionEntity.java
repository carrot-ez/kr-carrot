package kr.carrot.Spring.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_champion")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChampionEntity {

    @Id
    @Column(name = "champion_key")
    private int key;

    @Column(name = "champion_name")
    private String name;

    @Builder
    private ChampionEntity(int key, String name) {
        this.key = key;
        this.name = name;
    }
}
