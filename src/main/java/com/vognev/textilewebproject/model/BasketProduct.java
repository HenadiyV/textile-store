package com.vognev.textilewebproject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * textilewebproject_3  29/12/2021-8:41
 */
@Entity
public class BasketProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private Long productId;
    private Double size;
    private Double price;
    private String img;
    private String info;
    private Date dat;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="basket_id")
    private Basket basket;

    public BasketProduct() {
    }

    public BasketProduct(Long productId, Double size, Double price, String img, String info, Date dat, Basket basket) {
        this.productId = productId;
        this.size = size;
        this.price = price;
        this.img = img;
        this.info = info;
        this.dat = dat;
        this.basket = basket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
