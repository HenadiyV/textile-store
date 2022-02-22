package com.vognev.textilewebproject.model;

/**
 * textilewebproject_2  29/09/2021-17:01
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "image_product")
public class ImageProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String imgProduct;

    private boolean showcase;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    private String info;



    public ImageProduct() {
    }

    public ImageProduct(String imgProduct, boolean showcase, String info) {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
