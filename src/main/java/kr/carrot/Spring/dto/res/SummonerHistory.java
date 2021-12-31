package kr.carrot.Spring.dto.res;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SummonerHistory {

    private List<GameInfo> gameInfos;

    public SummonerHistory(List<GameInfo> gameInfos) {
        this.gameInfos = gameInfos;
    }
}
