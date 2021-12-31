package kr.carrot.Spring.dto;

import kr.carrot.Spring.exception.NotFoundException;
import lombok.Data;

import java.util.List;

@Data
public class MatchDto {
    private MetadataDto metadata;
    private InfoDto info;

    public ParticipantDto getParicipant(String summonerName) {
        if (info == null) {
            return null;
        }

        return info.getParticipants().stream()
                .filter(participant -> {
                    return summonerName.equals(participant.getSummonerName());
                })
                .findFirst()
                .orElse(null);
    }
}
