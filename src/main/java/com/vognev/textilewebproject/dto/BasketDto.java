package com.vognev.textilewebproject.dto;

import java.util.List;

/**
 * textilewebproject_3  27/12/2021-20:33
 */
public class BasketDto {

    private Long id;
    private String name;
    private String phone;
    private String info;
    private String token;
    private String dat;
    private String dat_clear;
    private Long userId;
    private List<BasketProductDto> basketProductDtoList;

    public BasketDto() {
    }

    public BasketDto(Long id,
                     String name,
                     String phone,
                     String info,
                     String token,
                     String dat,
                     String dat_clear,
                     Long userId
    ) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.info = info;
        this.token = token;
        this.dat = dat;
        this.dat_clear = dat_clear;
        this.userId = userId;
    }

    public BasketDto(String name,
                     String phone,
                     String info,
                     String token//,
                   //  Long userId
    ) {
        this.name = name;
        this.phone = phone;
        this.info = info;
        this.token = token;
        //this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Long getUserId() {
        return userId;
    }

    public List<BasketProductDto> getBasketProductDtoList() {
        return basketProductDtoList;
    }
}
