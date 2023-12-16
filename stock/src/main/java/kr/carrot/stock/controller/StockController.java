package kr.carrot.stock.controller;

import kr.carrot.stock.infrastructure.http.client.StockClient;
import kr.carrot.stock.infrastructure.http.request.StockPriceInfoRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class StockController {
    private final StockClient stockClient;

    @GetMapping
    public Object getAllStocks() {
        return stockClient.getStockPriceInfo(new StockPriceInfoRequest(1, 2));
    }

    public StockController(StockClient stockClient) {
        this.stockClient = stockClient;
    }
}
