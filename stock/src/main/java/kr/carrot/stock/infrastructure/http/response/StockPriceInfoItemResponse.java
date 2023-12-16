package kr.carrot.stock.infrastructure.http.response;

import kr.carrot.stock.infrastructure.http.request.MarketCategory;

public record StockPriceInfoItemResponse(
        String basDt, // 기준일자
        String srtnCd, // 단축코드
        String isinCd, // ISIN코드
        String itmsNm, // 종목명
        MarketCategory mrktCtg, // 시장구분
        String clpr, // 종가
        String vs, // 대비
        String fltRt, // 등락률
        String mkp, // 시가
        String hipr, // 고가
        String lopr, // 저가
        String trqu, // 거래량
        String trPrc, // 거래대금
        String lstgStCnt, // 상장주식수
        String intmrktTotAmt // 시가총액
) { }
