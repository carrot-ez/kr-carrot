package kr.carrot.stock.domain;

public enum StockMarket {
    KOSPI("코스피"),
    KOSDAQ("코스닥"),
    KONEX("코넥스"),
    ;

    String desc;

    StockMarket(String desc) {
        this.desc = desc;
    }
}
