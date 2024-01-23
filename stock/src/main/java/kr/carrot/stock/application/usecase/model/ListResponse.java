package kr.carrot.stock.application.usecase.model;

import java.util.List;

public record ListResponse<T>(
        List<T> data
) {
    public static <T> ListResponse<T> of(List<T> data) {
        return new ListResponse<>(data);
    }
}
