package kr.carrot.stock.infrastructure.http.request;

public class StockPriceInfoRequest {
    public int pageNo = 1; // 1부터 시작
    public int numOfRows = 10;
    public ResultType resultType = ResultType.json;
    public String basDt; // == 기간
    public String beginBasDt; // >= 기간
    public String endBasDt; // < 기간
    public String likeBasDt; // 기간을 포함하는 경우
    public String mrktCls; // 시장구분(코스닥, 나스닥)
    public int beginVs; // 대비
    public int endVs; // 대비
    public int beginFltRt; // 등락률
    public int endFltRt; // 등락률
    public int beginTrqu; // 거래량
    public int endTrqu; // 거래량
    public int beginTrPrc; // 거래대금
    public int endTrPrc; // 거래대금
    public int beginLstgStCnt; // 상장주식수
    public int endLstgStCnt; // 상장주식수
    public int beginMrktTotAmt; // 시가총액
    public int endMrktTotAmt;// 시가총액

    public StockPriceInfoRequest(int pageNo, int numOfRows) {
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
    }
}