package com.vognev.textilewebproject.model.dto;

/**
 * textilewebproject_3  18/11/2021-8:36
 */
public class PostOfficeUserDto {
    private Long id;
    private Long userId;
    private String postOffice;

    private String info;

    public PostOfficeUserDto() {
    }

    public PostOfficeUserDto(Long id, Long userId, String postOffice, String info) {
        this.id = id;
        this.userId = userId;
        this.postOffice = postOffice;

        this.info = info;
    }

    public PostOfficeUserDto(Long id, String postOffice) {
        this.id = id;
        this.postOffice = postOffice;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public String getInfo() {
        return info;
    }
}
