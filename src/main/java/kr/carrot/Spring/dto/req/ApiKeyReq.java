package kr.carrot.Spring.dto.req;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiKeyReq {
    private String apiKey;

    @Builder
    public ApiKeyReq(String apiKey) {
        this.apiKey = apiKey;
    }
}
