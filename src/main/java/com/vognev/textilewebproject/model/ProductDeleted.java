package com.vognev.textilewebproject.model;

/**
 * textilewebproject_2  16/10/2021-8:39
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="product_deleted")
public class ProductDeleted {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private Long product_id;
    private String name;
    private double length_cloth;
    private double purchase_price;
    private double selling_price;
    private double sales_length;
    private Date dat;
    private String info;
    private boolean active;
    private boolean singleton;
    private Date dat_delete;

    public ProductDeleted() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLength_cloth() {
        return length_cloth;
    }

    public void setLength_cloth(double length_cloth) {
        this.length_cloth = length_cloth;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }

    public double getSales_length() {
        return sales_length;
    }

    public void setSales_length(double sales_length) {
        this.sales_length = sales_length;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public Date getDat_delete() {
        return dat_delete;
    }

    public void setDat_delete(Date dat_delete) {
        this.dat_delete = dat_delete;
    }
}
