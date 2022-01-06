package com.vognev.textilewebproject.model.dto;

/**
 * textilewebproject_3  27/12/2021-20:33
 */
public class BasketProductDto {
    private Long id;
    private Long productId;
    private String info;
    private String token;
    private double size;
    private double price;
    private String img;
    private String dat;

    public BasketProductDto() {
    }

    public BasketProductDto(Long productId, String info, double size, double price, String img,String token) {
        this.productId = productId;
        this.info = info;
        this.size = size;
        this.price = price;
        this.img = img;
        this.token = token;
    }

    public BasketProductDto(Long id, Long productId, String info, String token, double size, double price, String img) {
        this.id = id;
        this.productId = productId;
        this.info = info;
        this.token = token;
        this.size = size;
        this.price = price;
        this.img = img;
    }



    public BasketProductDto(Long id,Long productId,  String info,
                            String token, double size, double price, String img, String dat) {
        this.id = id;
        this.productId = productId;
        this.info = info;
        this.token = token;
        this.size = size;
        this.price = price;
        this.img = img;
        this.dat = dat;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getInfo() {
        return info;
    }

    public String getToken() {
        return token;
    }

    public double getSize() {
        return size;
    }

    public String getImg() {
        return img;
    }

    public String getDat() {
        return dat;
    }

    public double getPrice() {
        return price;
    }
}
