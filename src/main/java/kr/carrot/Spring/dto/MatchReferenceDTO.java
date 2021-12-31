package kr.carrot.Spring.dto;

import lombok.Data;

@Data
public class MatchReferenceDTO {
    private String platformId;
    private String gameId;
    private int champion;
    private int queue;
    private int season;
    private long timestamp;
    private String role;
    private String lane;
}
