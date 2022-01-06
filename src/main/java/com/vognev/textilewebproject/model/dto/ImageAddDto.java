package com.vognev.textilewebproject.model.dto;

/**
 * textilewebproject_3  10/12/2021-18:38
 */
public class ImageAddDto {
    private Long productId;
    private String[] files;

    public ImageAddDto() {
    }

    public ImageAddDto(Long productId, String[] files) {
        this.productId = productId;
        this.files = files;
    }

    public Long getProductId() {
        return productId;
    }

    public String[] getFiles() {
        return files;
    }
}
