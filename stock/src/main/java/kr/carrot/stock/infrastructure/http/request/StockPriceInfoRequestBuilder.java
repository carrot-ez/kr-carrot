package kr.carrot.stock.infrastructure.http.request;

import jakarta.validation.constraints.NotNull;

public final class StockPriceInfoRequestBuilder {
    private @NotNull int pageNo;
    private @NotNull int numOfRows;
    private String basDt;
    private String beginBasDt;
    private String endBasDt;
    private String likeBasDt;
    private MarketCategory mrktCls;
    private String beginVs;
    private String endVs;
    private String beginFltRt;
    private String endFltRt;
    private String beginTrqu;
    private String endTrqu;
    private String beginTrPrc;
    private String endTrPrc;
    private String beginLstgStCnt;
    private String endLstgStCnt;
    private String beginMrktTotAmt;
    private String endMrktTotAmt;

    public static StockPriceInfoRequestBuilder builder(int pageNo, int numOfRows) {
        return new StockPriceInfoRequestBuilder()
                .pageNo(pageNo)
                .numOfRows(numOfRows);
    }

    private StockPriceInfoRequestBuilder() {
    }

    public StockPriceInfoRequestBuilder pageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public StockPriceInfoRequestBuilder numOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
        return this;
    }

    public StockPriceInfoRequestBuilder basDt(String basDt) {
        this.basDt = basDt;
        return this;
    }

    public StockPriceInfoRequestBuilder beginBasDt(String beginBasDt) {
        this.beginBasDt = beginBasDt;
        return this;
    }

    public StockPriceInfoRequestBuilder endBasDt(String endBasDt) {
        this.endBasDt = endBasDt;
        return this;
    }

    public StockPriceInfoRequestBuilder likeBasDt(String likeBasDt) {
        this.likeBasDt = likeBasDt;
        return this;
    }

    public StockPriceInfoRequestBuilder mrktCls(MarketCategory mrktCls) {
        this.mrktCls = mrktCls;
        return this;
    }

    public StockPriceInfoRequestBuilder beginVs(String beginVs) {
        this.beginVs = beginVs;
        return this;
    }

    public StockPriceInfoRequestBuilder endVs(String endVs) {
        this.endVs = endVs;
        return this;
    }

    public StockPriceInfoRequestBuilder beginFltRt(String beginFltRt) {
        this.beginFltRt = beginFltRt;
        return this;
    }

    public StockPriceInfoRequestBuilder endFltRt(String endFltRt) {
        this.endFltRt = endFltRt;
        return this;
    }

    public StockPriceInfoRequestBuilder beginTrqu(String beginTrqu) {
        this.beginTrqu = beginTrqu;
        return this;
    }

    public StockPriceInfoRequestBuilder endTrqu(String endTrqu) {
        this.endTrqu = endTrqu;
        return this;
    }

    public StockPriceInfoRequestBuilder beginTrPrc(String beginTrPrc) {
        this.beginTrPrc = beginTrPrc;
        return this;
    }

    public StockPriceInfoRequestBuilder endTrPrc(String endTrPrc) {
        this.endTrPrc = endTrPrc;
        return this;
    }

    public StockPriceInfoRequestBuilder beginLstgStCnt(String beginLstgStCnt) {
        this.beginLstgStCnt = beginLstgStCnt;
        return this;
    }

    public StockPriceInfoRequestBuilder endLstgStCnt(String endLstgStCnt) {
        this.endLstgStCnt = endLstgStCnt;
        return this;
    }

    public StockPriceInfoRequestBuilder beginMrktTotAmt(String beginMrktTotAmt) {
        this.beginMrktTotAmt = beginMrktTotAmt;
        return this;
    }

    public StockPriceInfoRequestBuilder endMrktTotAmt(String endMrktTotAmt) {
        this.endMrktTotAmt = endMrktTotAmt;
        return this;
    }

    public StockPriceInfoRequest build() {
        return new StockPriceInfoRequest(pageNo, numOfRows, basDt, beginBasDt, endBasDt, likeBasDt, mrktCls, beginVs, endVs, beginFltRt, endFltRt, beginTrqu, endTrqu, beginTrPrc, endTrPrc, beginLstgStCnt, endLstgStCnt, beginMrktTotAmt, endMrktTotAmt);
    }
}
