package kr.carrot.stock.infrastructure.http.request;

public class StockPriceInfoRequest {
    public int pageNo = 1; // 1부터 시작
    public int numOfRows = 10;
    public ResultType resultType = ResultType.json;
    public String basDt; // == 기간
    public String beginBasDt; // >= 기간
    public String endBasDt; // < 기간
    public String likeBasDt; // 기간을 포함하는 경우
    public MarketCategory mrktCls; // 시장구분(코스닥, 나스닥)
    public String beginVs; // 대비
    public String endVs; // 대비
    public String beginFltRt; // 등락률
    public String endFltRt; // 등락률
    public String beginTrqu; // 거래량
    public String endTrqu; // 거래량
    public String beginTrPrc; // 거래대금
    public String endTrPrc; // 거래대금
    public String beginLstgStCnt; // 상장주식수
    public String endLstgStCnt; // 상장주식수
    public String beginMrktTotAmt; // 시가총액
    public String endMrktTotAmt;// 시가총액

    public StockPriceInfoRequest(int pageNo, int numOfRows) {
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
    }
}