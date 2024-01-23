package kr.carrot.stock.domain;

import jakarta.validation.constraints.NotNull;

public class Stock {
    @NotNull private final Integer price;
    @NotNull private final Integer highPrice;
    @NotNull private final Integer lowPrice;
    @NotNull private final String name;
    @NotNull private final StockMarket market;
    @NotNull private final Long totalAmount;
    @NotNull private final Integer totalCount;

    public Stock(Integer price, Integer highPrice, Integer lowPrice, String name, StockMarket market, Long totalAmount, Integer totalCount) {
        this.price = price;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.name = name;
        this.market = market;
        this.totalAmount = totalAmount;
        this.totalCount = totalCount;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getHighPrice() {
        return highPrice;
    }

    public Integer getLowPrice() {
        return lowPrice;
    }

    public String getName() {
        return name;
    }

    public StockMarket getMarket() {
        return market;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }
}
