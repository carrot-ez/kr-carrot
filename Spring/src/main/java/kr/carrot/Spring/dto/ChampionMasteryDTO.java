package kr.carrot.Spring.dto;

import lombok.Data;

@Data
public class ChampionMasteryDTO {
    private long championPointsUntilNextLevel;
    private boolean chestGranted;
    private long championId;
    private long lastPlayTime;
    private int championLevel;
    private String summonerId;
    private int championPoints;
    private long championPointsSinceLastLevel;
    private int tokensEarned;
}
