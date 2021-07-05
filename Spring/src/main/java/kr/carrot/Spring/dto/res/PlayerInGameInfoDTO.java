package kr.carrot.Spring.dto.res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerInGameInfoDTO {

    private String summonerName;
    private int profileIcon;
    private int championId;
    private int spell1Id;
    private int spell2Id;
    private String highestAchievedSeasonTier;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int goldEarned;
    private int deaths;
    private int kills;
    private int assists;
    private boolean win;
}
