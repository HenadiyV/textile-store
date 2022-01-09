package com.vognev.textilewebproject.model.dto;

import java.util.List;

/**
 * textilewebproject_3  27/12/2021-20:33
 */
public class BasketDto {

    private Long id;
    private String username;
    private String phone;
    private String info;
    private String token;
    private String dat;
    private String dat_clear;
    private List<BasketProductDto> basketProductDtoList;

    public BasketDto() {
    }

    public BasketDto(Long id,String username, String phone, String info, String token, String dat,String dat__clear) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.info = info;
        this.token = token;
        this.dat = dat;
        this.dat_clear = dat_clear;
    }

    public BasketDto(String username, String phone, String info, String token) {
        this.username = username;
        this.phone = phone;
        this.info = info;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getInfo() {
        return info;
    }

    public String getToken() {
        return token;
    }

    public String getDat() {
        return dat;
    }

    public String getDat_clear() {
        return dat_clear;
    }

    public List<BasketProductDto> getBasketProductDtoList() {
        return basketProductDtoList;
    }
}
