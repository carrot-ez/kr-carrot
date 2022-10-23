package carrot.ez.riotApi.dto;

import lombok.Data;

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
