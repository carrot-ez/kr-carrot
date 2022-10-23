package carrot.ez.riotApi.dto;

import lombok.Data;

@Data
public class PlayerDTO {
    private int profileIcon;
    private String accountId;
    private String matchHistoryUri;
    private String currentAccountId;
    private String currentPlatformId;
    private String summonerName;
    private String summonerId;
    private String platformId;
}
