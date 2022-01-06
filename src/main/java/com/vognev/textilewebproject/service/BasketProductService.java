package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.BasketProduct;
import com.vognev.textilewebproject.repository.BasketProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * textilewebproject_3  29/12/2021-19:41
 */
@Service
public class BasketProductService {

    @Autowired
    private BasketProductRepository basketProductRepository;

    @Autowired
    private ProductService productService;


    void saveBasketProduct(BasketProduct basketProduct){
        basketProductRepository.save(basketProduct);
    }


    void deleteBasketProduct(Long id){

        BasketProduct basketProduct=basketProductRepository.getById(id);

        productService.updateProductSallingSize(basketProduct.getProductId(),basketProduct.getSize(),true);

        basketProductRepository.delete(basketProduct);
    }


   List<BasketProduct> getBasketProductList(Long basketId){

        return basketProductRepository.getBasketProductList(basketId);
    }
}
