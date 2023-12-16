package kr.carrot.stock.controller;

import kr.carrot.core.infra.http.client.datagov.StockClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class StockController {
    private final StockClient stockClient;

    @GetMapping
    public Object getAllStocks() {
        return stockClient.getStock();
    }

    public StockController(StockClient stockClient) {
        this.stockClient = stockClient;
    }
}
