package kr.carrot.Spring.dto.res;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SummonerHistory {
    private String summonerName;
    private long summonerLevel;
    private int profileIcon;
    private int win;
    private int lose;
    private List<InGamePlayerInfo> inGamePlayerInfos;

    @Builder
    public SummonerHistory(String summonerName, long summonerLevel, int profileIcon, int win, int lose, List<InGamePlayerInfo> inGamePlayerInfos) {
        this.summonerName = summonerName;
        this.summonerLevel = summonerLevel;
        this.profileIcon = profileIcon;
        this.win = win;
        this.lose = lose;
        this.inGamePlayerInfos = inGamePlayerInfos;
    }
}
