package kr.carrot.Spring.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchDTO {
    private long gameId;
    private List<ParticipantIdentityDTO> participantIdentities;
    private int queueId;
    private String gameType;
    private long gameDuration;
    private List<TeamStatsDTO> teams;
    private String platformId;
    private long gameCreation;
    private int seasonId;
    private String gameVersion;
    private int mapId;
    private String gameMode;
    private List<ParticipantDTO> participants;
}
