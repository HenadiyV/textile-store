package com.vognev.textilewebproject.dto;

import java.util.List;

/**
 * textilewebproject_2  10/10/2021-20:14
 */
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Boolean active;
    private int pageNum;
    private List<PhoneUserDto> phoneList;
    private List<AddressUserDto> addressList;
    private List<PostOfficeUserDto>postOfficeList;

    public UserDto(Long id, String name, String username, String email, Boolean active
    ) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.active = active;
    }

    public UserDto(Long id,
                   String name,
                   String username,
                   String email,
                   Boolean active,
                   int pageNum,
                   List<PhoneUserDto> phoneList,
                   List<AddressUserDto> addressList,
                   List<PostOfficeUserDto> postOfficeList
    ) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.active = active;
        this.pageNum = pageNum;
        this.phoneList = phoneList;
        this.addressList = addressList;
        this.postOfficeList = postOfficeList;
    }



    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActive() {
        return active;
    }

    public String getName() {
        return name;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<PhoneUserDto> getPhoneList() {
        return phoneList;
    }

    public List<AddressUserDto> getAddressList() {
        return addressList;
    }

    public List<PostOfficeUserDto> getPostOfficeList() {
        return postOfficeList;
    }
}
