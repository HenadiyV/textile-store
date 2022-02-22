package com.vognev.textilewebproject.dto;

import java.util.Date;

/**
 * textile-store  18/02/2022-14:08
 */
public class ProductStatistic {
    private Date dat;
    private double bought;
    private Long id;
    private double purchese;
    private double selling;
    private double size_selling;
    private double size_product;

    private double sold;
    private double sale_price;
    private double discount_price;

    /*crt.sale_price,crt.siz, crt.discount_price*/
    public ProductStatistic() {
    }

    public ProductStatistic(Date dat, double bought,Long id) {
        this.dat = dat;
        this.bought = bought;
        this.id = id;
    }

    public ProductStatistic(Date dat, double purchese, double selling, double size_selling, double size_product) {
        this.dat = dat;
        this.purchese = purchese;
        this.selling = selling;
        this.size_selling = size_selling;
        this.size_product = size_product;
    }
    public ProductStatistic(Date dat, double sale_price, double size_selling, double discount_price) {
        this.dat = dat;
        this.purchese = sale_price;
        this.size_selling = size_selling;
        this.discount_price = discount_price;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public double getPurchese() {
        return purchese;
    }

    public void setPurchese(double purchese) {
        this.purchese = purchese;
    }

    public double getSelling() {
        return selling;
    }

    public void setSelling(double selling) {
        this.selling = selling;
    }

    public double getSize_selling() {
        return size_selling;
    }

    public void setSize_selling(double size_selling) {
        this.size_selling = size_selling;
    }

    public double getSize_product() {
        return size_product;
    }

    public void setSize_product(double size_product) {
        this.size_product = size_product;
    }

    public double getSumm_selling() {
        return size_selling * selling;
    }

    public double getSumm_purchese() {
        return size_product * purchese;
    }

    public double getBought() {
        return bought;
    }

    public void setBought(double bought) {
        this.bought = bought;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
