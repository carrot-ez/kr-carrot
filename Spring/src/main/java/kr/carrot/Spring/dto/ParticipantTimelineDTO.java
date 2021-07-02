package kr.carrot.Spring.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ParticipantTimelineDTO {
    private int participantId;
    private String role;
    private String lane;
    private Map<String, Double> csDiffPerMinDeltas;
    private Map<String, Double> damageTakenPerMinDeltas;
    private Map<String, Double> damageTakenDiffPerMinDeltas;
    private Map<String, Double> xpPerMinDeltas;
    private Map<String, Double> xpDiffPerMinDeltas;
    private Map<String, Double> creepsPerMinDeltas;
    private Map<String, Double> goldPerMinDeltas;
}
