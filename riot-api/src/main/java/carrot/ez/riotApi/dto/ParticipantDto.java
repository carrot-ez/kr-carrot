package carrot.ez.riotApi.dto;

import lombok.Data;

@Data
public class ParticipantDto {
    private int assists;
    private int kills;
    private int deaths;
    private int championId;
    private String championName;
    private int champLevel;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int summoner1Id;
    private String summonerSpell1;
    private int summoner2Id;
    private String summonerSpell2;
    private int summonerLevel;
    private String summonerName;
    private int timePlayed;
    private boolean win;

    public void setSummonerSpell(String spell1, String spell2) {
        this.summonerSpell1 = spell1;
        this.summonerSpell2 = spell2;
    }
}
