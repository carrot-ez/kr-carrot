package kr.carrot.Spring.dto.res;

import kr.carrot.Spring.dto.ParticipantDto;
import lombok.Data;

@Data
public class GameInfo {
    private long gameDuration;
    private long gameEndTimestamp;
    private long gameStartTimestamp;
    private ParticipantDto participant;

    public GameInfo(long gameDuration, long gameEndTimestamp, long gameStartTimestamp, ParticipantDto participant) {
        this.gameDuration = gameDuration;
        this.gameEndTimestamp = gameEndTimestamp;
        this.gameStartTimestamp = gameStartTimestamp;
        this.participant = participant;
    }
}
