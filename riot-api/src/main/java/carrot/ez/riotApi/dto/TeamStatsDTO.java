package carrot.ez.riotApi.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamStatsDTO {
    private int towerKills;
    private int riftHeraldKills;
    private boolean firstBlood;
    private int inhibitorKills;
    private List<TeamBansDTO> bans;
    private boolean firstBaron;
    private boolean firstDragon;
    private int dominionVictoryScore;
    private int dragonKills;
    private int baronKills;
    private boolean firstInhibitor;
    private boolean firstTower;
    private int vilemawKills;
    private boolean firstRiftHerald;
    private int teamId;
    private String win;
}
