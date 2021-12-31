package kr.carrot.Spring.dto;

import lombok.Data;

import java.util.List;

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
    private int summoner2Id;
    private int summonerLevel;
    private String summonerName;
    private int timePlayed;
    private boolean win;
}
