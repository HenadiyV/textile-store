package com.vognev.textilewebproject.model.dto;

/**
 * textilewebproject_3  09/11/2021-16:46
 */
public class ImageProductDto {
    private Long id;
    private Long product_id;
    private String imgProduct;
    private boolean showcase;
    private String info;

    public ImageProductDto() {
    }

    public ImageProductDto(Long id,
                           Long product_id,
                           String imgProduct,
                           boolean showcase,
                           String info
    ) {
        this.id = id;
        this.product_id = product_id;
        this.imgProduct = imgProduct;
        this.showcase = showcase;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public boolean isShowcase() {
        return showcase;
    }

    public void setShowcase(boolean showcase) {
        this.showcase = showcase;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
