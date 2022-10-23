package carrot.ez.riotApi.dto;

import lombok.Data;

import java.util.List;

@Data
public class InfoDto {
    private long gameCreation;
    private long gameDuration;
    private long gameEndTimestamp;
    private long gameStartTimestamp;
    private long gameId;
    private int mapId;
    private List<ParticipantDto> participants;

}
