package com.vognev.textilewebproject.model;

/**
 * textilewebproject_3  27/12/2021-20:39
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
//@Transactional
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String token;
    private String username;
    private String phone;
    private String info;
    private Date dat;
    private Date dat_clear;
    private Long userId;
    @OneToMany(mappedBy = "basket",fetch = FetchType.LAZY,cascade = CascadeType.ALL)//
    private List<BasketProduct> basketProducts;

    public Basket() {
    }

    public Basket(String token, String username, String phone, String info, Date dat, Date dat_clear,Long userId) {
        this.token = token;
        this.username = username;
        this.phone = phone;
        this.info = info;
        this.dat = dat;
        this.dat_clear = dat_clear;
        this.userId=userId;
    }

    public Basket(Long id, String token, String username, String phone, String info, Date dat, Date dat_clear,Long userId, List<BasketProduct> basketProducts) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.phone = phone;
        this.info = info;
        this.dat = dat;
        this.dat_clear = dat_clear;
        this.userId=userId;
        this.basketProducts = basketProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Date getDat_clear() {
        return dat_clear;
    }

    public void setDat_clear(Date dat_clear) {
        this.dat_clear = dat_clear;
    }

    public List<BasketProduct> getBasketProducts() {
        return basketProducts;
    }

    public void setBasketProducts(List<BasketProduct> basketProducts) {
        this.basketProducts = basketProducts;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
