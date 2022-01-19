package com.vognev.textilewebproject.model.dto;

import java.util.List;

/**
 * textilewebproject_3  27/11/2021-19:14
 */
public class AddOrderDto {
    private Long userId;
    private Long phoneId;
    private Long addressId;
    private Long postOfficeId;
    private CartDto[] carts;

    public AddOrderDto(Long userId,
                       Long phoneId,
                       Long addressId,
                       Long postOfficeId,
                       CartDto[] carts
    ) {
        this.userId = userId;
        this.phoneId = phoneId;
        this.addressId = addressId;
        this.postOfficeId = postOfficeId;
        this.carts = carts;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public Long getPostOfficeId() {
        return postOfficeId;
    }

    public CartDto[] getCarts() {
        return carts;
    }
}
