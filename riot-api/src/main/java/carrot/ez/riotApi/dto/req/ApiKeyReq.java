package carrot.ez.riotApi.dto.req;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiKeyReq {
    private String apiKey;

    @Builder
    public ApiKeyReq(String apiKey) {
        this.apiKey = apiKey;
    }
}
