package com.vognev.textilewebproject.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * textilewebproject_3  03/11/2021-17:44
 */
public class AddCartToDto {
    private Long orderId;
    private Long productId;
    private String productName;
    private Double productBalance;
    private Double sellingPrice;
    private Double metr;
    private Double bonus;
    private String info;

    public AddCartToDto() {
    }
    @JsonCreator
    public AddCartToDto(
            @JsonProperty("orderId")Long orderId,
            @JsonProperty("productId")Long productId,
            @JsonProperty("productName")String productName,
            @JsonProperty("productBalance")Double productBalance,
            @JsonProperty("sellingPrice")Double sellingPrice,
            @JsonProperty("metr")Double metr,
            @JsonProperty("bonus")Double bonus,
            @JsonProperty("info")String info
    ) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.productBalance = productBalance;
        this.sellingPrice = sellingPrice;
        this.metr = metr;
        this.bonus = bonus;
        this.info = info;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductBalance() {
        return productBalance;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public Double getMetr() {
        return metr;
    }

    public Double getBonus() {
        return bonus;
    }

    public String getInfo() {
        return info;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductBalance(Double productBalance) {
        this.productBalance = productBalance;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setMetr(Double metr) {
        this.metr = metr;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
