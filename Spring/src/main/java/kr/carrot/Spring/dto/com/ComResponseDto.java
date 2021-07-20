package kr.carrot.Spring.dto.com;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ComResponseDto<T> {
    private boolean success;
    private T response;
    private String errorMsg;

    /**
     * 성공 응답 생성
     */
    public static <T> ComResponseDto<T> success(T response) {
        return new ComResponseDto<T>(true, response, null);
    }

    /**
     * 실패 응답 생성
     */
    public static <T> ComResponseDto<T> fail(String errorMsg) {
        return new ComResponseDto<>(false, null, errorMsg);
    }
}
