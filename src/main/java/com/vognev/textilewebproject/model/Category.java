package com.vognev.textilewebproject.model;

/**
 * textilewebproject_2  29/09/2021-13:21
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category implements
        Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer id;

    private String info;

    @NotBlank(message = "Введіть назву категорії.")
    private String name;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)//cascade = CascadeType.ALL,
    private List<Product> products;

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
