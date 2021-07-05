package kr.carrot.Spring.dto.res;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SummonerHistory {
    private int win;
    private int lose;
    private List<InGamePlayerInfo> inGamePlayerInfos;
}
