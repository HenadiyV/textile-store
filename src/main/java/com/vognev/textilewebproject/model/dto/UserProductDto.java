package com.vognev.textilewebproject.model.dto;

import com.vognev.textilewebproject.model.ImageProduct;

import java.util.Date;
import java.util.Set;

/**
 * textilewebproject_3  03/12/2021-9:35
 */
public class UserProductDto {
    private Long id;
    private String name;
    private String username;
    private String phone;
    private String address;
    private String postOffice;
    private Long cartId;
    private Long productId;
    private String nameProduct;
    private String color;
    private double sizeProduct;
    private double purchasePrice;
    private double sellingPrice;
    private boolean active;
    private String description;
    private Date dat;
    private Double selling_size;
    private String info;
    private String category;
    private Double product_balance;
    private Double metr;
    private Double bonus;
    private Set<ImageProduct> imageProducts;

    public UserProductDto(Long id, String name, String username, String phone, String address, String postOffice,Long cartId, String category,
                          Long productId, String nameProduct, String color, double sizeProduct, double purchasePrice,
                          double sellingPrice, String description, Date dat, Double selling_size, String info
                          ) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.postOffice = postOffice;
        this.cartId = cartId;
        this.category = category;
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.color = color;
        this.sizeProduct = sizeProduct;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.description = description;
        this.dat = dat;
        this.selling_size = selling_size;
        this.info = info;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public Long getProductId() {
        return productId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getColor() {
        return color;
    }

    public double getSizeProduct() {
        return sizeProduct;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public boolean isActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }

    public Date getDat() {
        return dat;
    }

    public Double getSelling_size() {
        return selling_size;
    }

    public String getInfo() {
        return info;
    }

    public String getCategory() {
        return category;
    }

    public Double getProduct_balance() {
        return product_balance;
    }

    public Double getMetr() {
        return metr;
    }

    public Double getBonus() {
        return bonus;
    }

    public Set<ImageProduct> getImageProducts() {
        return imageProducts;
    }

    public Long getCartId() {
        return cartId;
    }
}
