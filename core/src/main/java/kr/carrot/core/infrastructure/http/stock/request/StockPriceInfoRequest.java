package kr.carrot.core.infrastructure.http.stock.request;

public record StockPriceInfoRequest(
        int pageNo, // 1부터 시작
        int numOfRows,
        String basDt, // == 기간
        String beginBasDt, // >= 기간
        String endBasDt, // < 기간
        String likeBasDt, // 기간을 포함하는 경우
        MarketCategory mrktCls, // 시장구분(코스닥, 나스닥)
        String beginVs, // 대비
        String endVs, // 대비
        String beginFltRt, // 등락률
        String endFltRt, // 등락률
        String beginTrqu, // 거래량
        String endTrqu, // 거래량
        String beginTrPrc, // 거래대금
        String endTrPrc, // 거래대금
        String beginLstgStCnt, // 상장주식수
        String endLstgStCnt, // 상장주식수
        String beginMrktTotAmt, // 시가총액
        String endMrktTotAmt// 시가총액
) { }