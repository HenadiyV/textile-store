package com.vognev.textilewebproject.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vognev.textilewebproject.model.Cart;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * textilewebproject_2  17/10/2021-8:59
 */
public class OrderDto {
    private Long id;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
    private Date dat_dispatch;
    private String status;
    private String delivery;
    private String info_order;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
    private Date dat_create;
    private List<CartDto> carts;
    private Double summ;
    private Long user_id;
    private String username;
    private String name;
    private Long phone_id;
    private String phone;
    private Long address_id;
    private String address;
    private String city;
    private String postCode;
    private Long postOffice_id;
    private String postOffice;


    public OrderDto(Long id, Date dat_dispatch, String status, String delivery, String info_order,
                    Date dat_create, List<CartDto> carts, Double summ, Long user_id, String username,
                    String name, Long phone_id, String phone, Long address_id, String address, String city,
                    String postCode, Long postOffice_id, String postOffice) {
        this.id = id;
        this.dat_dispatch = dat_dispatch;
        this.status = status;
        this.delivery = delivery;
        this.info_order = info_order;
        this.dat_create = dat_create;
        this.carts = carts;
        this.summ = summ;
        this.user_id = user_id;
        this.username = username;
        this.name = name;
        this.phone_id = phone_id;
        this.phone = phone;
        this.address_id = address_id;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.postOffice_id = postOffice_id;
        this.postOffice = postOffice;
    }

    public OrderDto(Long id, Date dat_dispatch, String status, String delivery, String info_order,
                    Date dat_create, Double summ, Long user_id, Long phone_id, Long address_id, Long postOffice_id) {
        this.id = id;
        this.dat_dispatch = dat_dispatch;
        this.status = status;
        this.delivery = delivery;
        this.info_order = info_order;
        this.dat_create = dat_create;
        this.summ = summ;
        this.user_id = user_id;
        this.phone_id = phone_id;
        this.address_id = address_id;
        this.postOffice_id = postOffice_id;
    }

    public Long getId() {
        return id;
    }

    public Date getDat_dispatch() {
        return dat_dispatch;
    }

    public String getStatus() {
        return status;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getInfo_order() {
        return info_order;
    }

    public Date getDat_create() {
        return dat_create;
    }

    public List<CartDto> getCarts() {
        return carts;
    }

    public Double getSumm() {
        return summ;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public Long getUser_id() {
        return user_id;
    }

    public Long getPhone_id() {
        return phone_id;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public Long getPostOffice_id() {
        return postOffice_id;
    }
}
