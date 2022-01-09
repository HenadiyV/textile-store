package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.Basket;
import com.vognev.textilewebproject.model.BasketProduct;
import com.vognev.textilewebproject.model.dto.BasketProductDto;
import com.vognev.textilewebproject.model.util.Constants;
import com.vognev.textilewebproject.repository.BasketProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private BasketService basketService;


    private void saveBasketProduct(BasketProduct basketProduct){
        basketProductRepository.save(basketProduct);
    }


    void deleteBasketProduct(Long id){

        BasketProduct basketProduct=basketProductRepository.getById(id);

        productService.updateProductSallingSize(basketProduct.getProductId(),basketProduct.getSize(),false);

        basketProductRepository.delete(basketProduct);
    }


   List<BasketProduct> getBasketProductList(Long basketId){

        return basketProductRepository.getBasketProductList(basketId);
    }


    public BasketProduct getBasketPorductById(Long id){
        try{
            return basketProductRepository.getById(id);
        }catch(Exception ex){
            System.out.println("ErrorgetBasketPorductById");
            ex.printStackTrace();
            return null;
        }
    }

    public void saveBasketProduct(BasketProductDto basketProductDto){

        Basket basket = basketService.getBasketToToken(basketProductDto.getToken());

        //Date dat=new Date();
        Date today=new Date();
        long ltime=today.getTime()+Constants.ONE_DAY;//3*
        Date today3=new Date(ltime);

        if(basket==null){
            basket= new Basket();
            basket.setToken(basketProductDto.getToken());
            basket.setDat_clear(today3);
            basket.setDat(today);
            basketService.saveBasket(basket);
        }
        productService.updateProductSallingSize(basketProductDto.getProductId(),basketProductDto.getSize(),true);

        BasketProduct basketProduct = new BasketProduct(
                basketProductDto.getProductId(),
                basketProductDto.getSize(),
                basketProductDto.getPrice(),
                basketProductDto.getImg(),
                basketProductDto.getInfo(),
                today,
                basket
        );

        saveBasketProduct(basketProduct);
    }
}
