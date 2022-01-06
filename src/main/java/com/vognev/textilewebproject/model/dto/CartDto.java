package com.vognev.textilewebproject.model.dto;

/**
 * textilewebproject_2  17/10/2021-18:40
 */
public class CartDto {
    private Long cartId;
    private Long orderId;
    private Long productId;
    private String productName;
    private Double sellingPrice;
    private Double sizeProduct;
    private Double balance;
    private Double siz;
    private Double summ;
    private Double discountPrice;
    private String infoCart;

    public CartDto() {
    }

    public CartDto(Long cartId,
                    Long orderId,
                     Long productId,
                      String productName,
                       Double sellingPrice,
                        Double sizeProduct,
                         Double balance,
                          Double siz,
                           Double summ,
                            Double discountPrice,
                             String infoCart
    ) {
        this.cartId = cartId;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.sellingPrice = sellingPrice;
        this.sizeProduct = sizeProduct;
        this.balance = balance;
        this.siz = siz;
        this.summ = summ;
        this.discountPrice = discountPrice;
        this.infoCart = infoCart;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public Double getSiz() {
        return siz;
    }

    public Double getSumm() {
        return summ;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public String getInfoCart() {
        return infoCart;
    }

    public Double getSizeProduct() {
        return sizeProduct;
    }

    public Double getBalance() {
        return balance;
    }
}
