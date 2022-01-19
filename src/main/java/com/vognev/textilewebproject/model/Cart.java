package com.vognev.textilewebproject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * textilewebproject_2  15/10/2021-10:53
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    private Long product_id;

    private double salePrice;

    private String nameProduct;

    private double siz;

    private double discount_price;

    private String info;

    @Temporal(TemporalType.DATE)
    private Date dat;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="order_id")
    private Order order;
    public Cart() {
    }

    public Cart(Long product_id, double salePrice, String nameProduct, double siz, double discount_price,
                String info, Date dat, Order order) {

        this.product_id = product_id;
        this.salePrice = salePrice;
        this.nameProduct = nameProduct;
        this.siz = siz;
        this.discount_price = discount_price;
        this.info = info;
        this.dat = dat;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public double getSiz() {
        return siz;
    }

    public void setSiz(double siz) {
        this.siz = siz;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }



}
