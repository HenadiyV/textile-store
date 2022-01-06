package com.vognev.textilewebproject.model.dto;

/**
 * textilewebproject_2  14/10/2021-7:26
 */
public class ProductWarehouseDto {
    private Double size;//метри
    private Double purchace;//закупочна ціна
    private Double selling;//продажна ціна
    private Double selling_size;//продано метрів
    private Double sum_purchace;//по закупочній
    private Double sum_selling;// по продажній
    private Double sum_remainder_purchace;//остаток по закупочній
    private Double remainder;//остаток
    private Double profit;//прибуток

    public ProductWarehouseDto() {
    }

    public ProductWarehouseDto(Double size, Double purchace, Double selling, Double selling_size,
                               Double sum_purchace, Double sum_selling, Double sum_remainder, Double remainder,Double profit) {
        this.size = size;
        this.purchace = purchace;
        this.selling = selling;//
        this.selling_size = selling_size;//
        this.sum_purchace = sum_purchace;//
        this.sum_selling = sum_selling;//
        this.sum_remainder_purchace = sum_remainder;//
        this.remainder = remainder;
        this.profit = profit;
    }

    public Double getSize() {
        return size;
    }

    public Double getPurchace() {
        return purchace;
    }

    public Double getSelling() {
        return selling;
    }

    public Double getSelling_size() {
        return selling_size;
    }

    public Double getRemainder() {
        return remainder;
    }

    public Double getSum_purchace() {
        return sum_purchace;
    }

    public Double getSum_selling() {
        return sum_selling;
    }

    public Double getSum_remainder_purchace() {
        return sum_remainder_purchace;
    }

    public Double getProfit() {
        return profit;
    }
}
