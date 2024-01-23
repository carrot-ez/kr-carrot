package kr.carrot.stock.domain;

import jakarta.validation.constraints.NotNull;

public final class StockBuilder {
    private @NotNull Integer price;
    private @NotNull Integer highPrice;
    private @NotNull Integer lowPrice;
    private @NotNull String name;
    private @NotNull StockMarket market;
    private @NotNull Long totalAmount;
    private @NotNull Integer totalCount;

    private StockBuilder() {
    }

    public static StockBuilder builder() {
        return new StockBuilder();
    }

    public StockBuilder price(Integer price) {
        this.price = price;
        return this;
    }

    public StockBuilder highPrice(Integer highPrice) {
        this.highPrice = highPrice;
        return this;
    }

    public StockBuilder lowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
        return this;
    }

    public StockBuilder name(String name) {
        this.name = name;
        return this;
    }

    public StockBuilder market(StockMarket market) {
        this.market = market;
        return this;
    }

    public StockBuilder totalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public StockBuilder totalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public Stock build() {
        return new Stock(price, highPrice, lowPrice, name, market, totalAmount, totalCount);
    }
}
