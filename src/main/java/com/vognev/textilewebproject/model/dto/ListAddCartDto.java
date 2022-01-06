package com.vognev.textilewebproject.model.dto;

import java.util.List;

/**
 * textilewebproject_3  03/11/2021-22:10
 */
public class ListAddCartDto {
    private List<AddCartToDto> addCartToDtoList;

    public List<AddCartToDto> getAddCartToDtoList() {
        return addCartToDtoList;
    }

    public void setAddCartToDtoList(List<AddCartToDto> addCartToDtoList) {
        this.addCartToDtoList = addCartToDtoList;
    }
}
