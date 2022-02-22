package com.vognev.textilewebproject.dto;

import com.vognev.textilewebproject.model.Category;
import com.vognev.textilewebproject.model.ImageProduct;

import java.util.Date;
import java.util.List;

/**
 * textilewebproject_3  31/10/2021-8:06
 */
public class ProductDto {
    private Long id;
    private String name;
    private String color;
    private double sizeProduct;
    private double purchasePrice;
    private double sellingPrice;
    private boolean active;
    private String description;
    private Date dat;
    private Double selling_size;
    private String info;
    private String img;
    private int category_id;
    private Double product_balance;
    private Double metr;
    private Double bonus;
    private List<ImageProduct> imageProducts;
    private Category category;
    private int positionList;

public ProductDto() {
}
    public ProductDto(Long id,
                      String name,
                      String color,
                      double sizeProduct,
                      double sellingPrice,
                      boolean active,
                      String description,
                      Date dat,
                      Double selling_size,
                      String info,
                      String img,
                      int positionList
    ) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.sizeProduct = sizeProduct;
        //this.purchasePrice = purchasePrice;//double purchasePrice,
        this.sellingPrice = sellingPrice;
        this.active = active;
        this.description = description;
        this.dat = dat;
        this.selling_size = selling_size;
        this.info = info;
        this.img = img;
        this.positionList=positionList;
    }

    public ProductDto(Long id,
                      String name,
                      String color,
                      double sizeProduct,
                      double sellingPrice,
                      boolean active,
                      String description,
                      Date dat,
                      Double selling_size,
                      String info
    ) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.sizeProduct = sizeProduct;
        this.sellingPrice = sellingPrice;
        this.active = active;
        this.description = description;
        this.dat = dat;
        this.selling_size = selling_size;
        this.info = info;

    }

    public ProductDto(Long id, String name, String color,double purchasePrice, double sizeProduct,  double sellingPrice,
                      boolean active, String description, Date dat, Double selling_size, String info,String img, int category_id,
                      Double product_balance, Double metr, Double bonus, List<ImageProduct> imageProducts,
                      Category category, int positionList) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.sizeProduct = sizeProduct;
       this.purchasePrice = purchasePrice;//
        this.sellingPrice = sellingPrice;
        this.active = active;
        this.description = description;
        this.dat = dat;
        this.selling_size = selling_size;
        this.info = info;
        this.img = img;
        this.category_id = category_id;
        this.product_balance = product_balance;
        this.metr = metr;
        this.bonus = bonus;
        this.imageProducts = imageProducts;
        this.category = category;
        this.positionList=positionList;
    }


    public ProductDto(Long id, Double selling_size) {
        this.id = id;
        this.selling_size = selling_size;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public int getCategory_id() {
        return category_id;
    }

    public List<ImageProduct> getImageProducts() {
        return imageProducts;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setProduct_balance(Double product_balance) {
        this.product_balance = product_balance;
    }

    public void setMetr(Double metr) {
        this.metr = metr;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public void setImageProducts(List<ImageProduct> imageProducts) {
        this.imageProducts = imageProducts;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getImg() {
        return img;
    }

    public int getPositionList() {
        return positionList;
    }
}
