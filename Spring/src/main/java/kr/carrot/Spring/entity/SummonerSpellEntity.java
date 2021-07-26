package kr.carrot.Spring.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_summoner_spell")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SummonerSpellEntity {

    @Id
    @Column(name = "spell_key")
    private int spellKey;

    @Column(name = "spell_name")
    private String spellName;

    @Builder
    private SummonerSpellEntity(int spellKey, String spellName) {
        this.spellKey = spellKey;
        this.spellName = spellName;
    }
}
