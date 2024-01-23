package kr.carrot.core.infrastructure.http.stock.response;

import java.util.List;

public record StockApiResponse<T>(Response<T> response) {
    public record Response<T>(Header header, Body<T> body) { }
    public record Header(String resultCode, String resultMsg) { }
    public record Body<T>(
            int numOfRows, // 한 페이지 결과 수
            int pageNo, // 페이지 번호
            int totalCount, // 전체 결과 수
            Items<T> items
    ) { }
    public record Items<T>(List<T> item) {}
}
