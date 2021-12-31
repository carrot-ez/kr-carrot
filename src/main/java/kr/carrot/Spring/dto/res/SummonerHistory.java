package kr.carrot.Spring.dto.res;

import lombok.Data;

import java.util.List;

@Data
public class SummonerHistory {

    private List<GameInfo> gameInfos;

    public SummonerHistory(List<GameInfo> gameInfos) {
        this.gameInfos = gameInfos;
    }
}
