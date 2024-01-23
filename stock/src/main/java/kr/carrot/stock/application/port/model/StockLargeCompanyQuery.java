package kr.carrot.stock.application.port.model;

public record StockLargeCompanyQuery(
        int size,
        long totalAmount,
        long totalStockCount
) {
}
