package com.vognev.textilewebproject.dto;

/**
 * textilewebproject_3  18/11/2021-7:15
 */
public class AddressUserDto {
    private Long id;

    private Long userId;

    private String region;

    private String district;

    private String city;

    private String address;

    private String postCode;

    private String info;

    private boolean active;

    public AddressUserDto() {
    }

    public AddressUserDto(
                          String region,
                          String district,
                          String city,
                          String address,
                          Long id,
                          String info,
                          String postCode,
                          Long userId
    ) {
        this.id = id;
        this.userId = userId;
        this.region = region;
        this.district = district;
        this.city = city;
        this.address = address;
        this.postCode = postCode;
        this.info = info;
    }

    public AddressUserDto(Long id,
                          String region,
                          String district,
                          String city,
                          String address,
                          String postCode,
                          boolean active
    ) {
        this.id = id;
        this.region = region;
        this.district = district;
        this.city = city;
        this.address = address;
        this.postCode = postCode;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRegion() {
        return region;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getInfo() {
        return info;
    }

    public boolean isActive() {
        return active;
    }
}
