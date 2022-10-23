package carrot.ez.riotApi.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InGamePlayerInfo {

    private String summonerName;
    private String champion;
    private String spell1;
    private String spell2;
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

    @Builder
    public InGamePlayerInfo(String summonerName, String champion, String spell1, String spell2, String highestAchievedSeasonTier, int item0, int item1, int item2, int item3, int item4, int item5, int item6, int goldEarned, int deaths, int kills, int assists, boolean win) {
        this.summonerName = summonerName;
        this.champion = champion;
        this.spell1 = spell1;
        this.spell2 = spell2;
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.goldEarned = goldEarned;
        this.deaths = deaths;
        this.kills = kills;
        this.assists = assists;
        this.win = win;
    }
}
