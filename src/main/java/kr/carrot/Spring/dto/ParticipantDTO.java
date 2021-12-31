package kr.carrot.Spring.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParticipantDTO {
    private int participantId;
    private int championId;
    private List<RuneDTO> runes;
    private ParticipantStatsDTO stats;
    private int teamId;
    private ParticipantTimelineDTO timeline;
    private int spell1Id;
    private int spell2Id;
    private String highestAchievedSeasonTier;
    private List<MasteryDTO> masteries;
}
