package kr.carrot.Spring.dto.res;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SummonerHistory {
    private String summonerName;
    private long summonerLevel;
    private int profileIcon;
    private int win;
    private int lose;
    private List<InGamePlayerInfo> inGamePlayerInfos;
}
