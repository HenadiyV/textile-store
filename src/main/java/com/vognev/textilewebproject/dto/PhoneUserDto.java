package com.vognev.textilewebproject.dto;

/**
 * textilewebproject_3  16/11/2021-10:57
 */
public class PhoneUserDto {

    private Long id;
    private Long userId;
    private String info;
    private String phone;
    private boolean active;

    public PhoneUserDto() {
    }


    public PhoneUserDto(Long id,
                        String info,
                        String phone,
                        Long userId
    ) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.info = info;
    }


    public PhoneUserDto(Long id,
                        String phone,
                        String info,
                        boolean active
    ) {
        this.id = id;
        this.phone = phone;
        this.info = info;
        this.active = active;
    }


    public PhoneUserDto(Long id,
                        String phone,
                        boolean active
    ) {
        this.id = id;
        this.phone = phone;
        this.active = active;
    }


    public Long getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public String getPhone() {
        return phone;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isActive() {
        return active;
    }
}
