package kr.carrot.stock.application.usecase.model;

import kr.carrot.stock.domain.Stock;
import kr.carrot.stock.domain.StockMarket;

public record StockResponse(
        String name,
        Integer price,
        StockMarket market
) {
    public static StockResponse of(Stock stock) {
        return new StockResponse(stock.getName(), stock.getPrice(), stock.getMarket());
    }
}
