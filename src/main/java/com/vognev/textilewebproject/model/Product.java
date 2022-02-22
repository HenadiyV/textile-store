package com.vognev.textilewebproject.model;

/**
 * textilewebproject_2  29/09/2021-13:12
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    @NotBlank(message = "Введіть назву тканини.")
    private String name;

    private String color;

    @DecimalMin(message = "Введіть метраж тканини.",value = "1", inclusive = false)
    private double sizeProduct;

   @DecimalMin(message = "Введіть закупочну ціну тканини.",value = "1", inclusive = false)
    private double purchasePrice;

    private double purchasePriceDollar;

    @DecimalMin(message = "Введіть продажну ціну тканини." ,value = "1", inclusive = false)
    private double sellingPrice;

    private boolean active;

    private String description;

    private Date dat;

    private Double selling_size;

    private String info;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)//,cascade = CascadeType.ALL
    private List<ImageProduct> imageProducts;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name="products_tags",
            joinColumns=@JoinColumn(name="product_id", referencedColumnName="id"),
           inverseJoinColumns=@JoinColumn(name="tag_id", referencedColumnName="id"))
            private Set<Tag> tags = new HashSet<>();

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(double sizeProduct) {
        this.sizeProduct = sizeProduct;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<ImageProduct> getImageProducts() {
        return imageProducts;
    }

    public void setImageProducts(List<ImageProduct> imageProducts) {
        this.imageProducts = imageProducts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSelling_size() {
        return selling_size;
    }

    public void setSelling_size(Double selling_size) {
        this.selling_size = selling_size;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public double getPurchasePriceDollar() {
        return purchasePriceDollar;
    }

    public void setPurchasePriceDollar(double purchasePriceDollar) {
        this.purchasePriceDollar = purchasePriceDollar;
    }
}
