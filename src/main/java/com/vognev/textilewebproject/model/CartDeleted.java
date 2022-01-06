package com.vognev.textilewebproject.model;

/**
 * textilewebproject_2  16/10/2021-8:20
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cart_deleted")
public class CartDeleted {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private Long user_id;
    private Long order_id;
    private Long cart_id;
    private Long product_id;
    private String name_product;
    private double siz;
    private double summ;
    private double discount_price;
    @Temporal(TemporalType.DATE)
    private Date dat_create;
    @Temporal(TemporalType.DATE)
    private Date dat_delete;
    private String info;

    public CartDeleted() {
    }

    public CartDeleted(Long user_id, Long order_id, Long cart_id, Long product_id, String name_product,
                       double siz, double summ, double discount_price, Date dat_create, Date dat_delete, String info) {
        this.user_id = user_id;
        this.order_id = order_id;
        this.cart_id = cart_id;
        this.product_id = product_id;
        this.name_product = name_product;
        this.siz = siz;
        this.summ = summ;
        this.discount_price = discount_price;
        this.dat_create = dat_create;
        this.dat_delete = dat_delete;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public double getSiz() {
        return siz;
    }

    public void setSiz(double siz) {
        this.siz = siz;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
    }

    public Date getDat_create() {
        return dat_create;
    }

    public void setDat_create(Date dat_create) {
        this.dat_create = dat_create;
    }

    public Date getDat_delete() {
        return dat_delete;
    }

    public void setDat_delete(Date dat_delete) {
        this.dat_delete = dat_delete;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
