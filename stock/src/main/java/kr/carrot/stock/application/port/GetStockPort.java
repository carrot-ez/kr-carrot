package kr.carrot.stock.application.port;

import kr.carrot.stock.application.port.model.StockLargeCompanyQuery;
import kr.carrot.stock.domain.Stock;

import java.util.List;

public interface GetStockPort {
    List<Stock> getLargeCompanyStocks(StockLargeCompanyQuery query);
}
