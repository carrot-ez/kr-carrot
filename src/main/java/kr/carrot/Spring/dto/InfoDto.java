package kr.carrot.Spring.dto;

import lombok.Data;

import java.util.List;

@Data
public class InfoDto {
    long gameCreation;
    long gameDuration;
    long gameEndTimestamp;
    long gameStartTimestamp;
    long gameId;
    int mapId;
    List<ParticipantDto> participants;

}
