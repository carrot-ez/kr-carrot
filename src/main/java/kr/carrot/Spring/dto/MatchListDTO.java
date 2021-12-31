package kr.carrot.Spring.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchListDTO {
    private List<MatchReferenceDTO> matches;
    private int startIndex;
    private int endIndex;
    private int totalGames;
}
