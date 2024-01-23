package kr.carrot.stock.application.usecase;

import kr.carrot.stock.application.port.GetStockPort;
import kr.carrot.stock.application.port.model.StockLargeCompanyQuery;
import kr.carrot.stock.application.usecase.model.ListResponse;
import kr.carrot.stock.application.usecase.model.StockResponse;
import kr.carrot.stock.domain.Stock;

import java.util.List;

public class GetLargeCompanyStocksUseCase {
    private static final long MIN_TOTAL_AMOUNT = 100_000_000L;
    private final static long MIN_TOTAL_COUNT = 1_000_000L;
    private final GetStockPort getStockPort;

    public GetLargeCompanyStocksUseCase(GetStockPort getStockPort) {
        this.getStockPort = getStockPort;
    }

    public ListResponse<StockResponse> get(int size) {
        List<Stock> stocks = getStockPort.getLargeCompanyStocks(new StockLargeCompanyQuery(size, MIN_TOTAL_AMOUNT, MIN_TOTAL_COUNT));
        List<StockResponse> stockResponses = stocks.stream().map(StockResponse::of).toList();
        return ListResponse.of(stockResponses);
    }
}
