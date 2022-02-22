package com.vognev.textilewebproject.dto;

import com.vognev.textilewebproject.model.Product;

import java.util.List;

/**
 * textile-store  09/01/2022-18:58
 */
public class ProductPageDto {
    private List<Product> productList;
    private int currentPage;
    private int totalPages;

    public ProductPageDto() {
    }

    public ProductPageDto(List<Product> productList,
                          int currentPage,
                          int totalPages
    ) {
        this.productList = productList;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
