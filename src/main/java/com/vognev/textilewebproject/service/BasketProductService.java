package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.Basket;
import com.vognev.textilewebproject.model.BasketProduct;
import com.vognev.textilewebproject.dto.BasketProductDto;
import com.vognev.textilewebproject.util.Constants;
import com.vognev.textilewebproject.repository.BasketProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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


    void deleteBasketProduct(Long id,boolean operator){

        BasketProduct basketProduct=basketProductRepository.getById(id);

    if(operator) {

        productService.updateProductSallingSize(basketProduct.getProductId(), basketProduct.getSize(), false);
    }
        basketProductRepository.delete(basketProduct);
    }


    List<BasketProduct> getBasketProductList(Long basketId){

        return basketProductRepository.getBasketProductList(basketId);
    }


    private BasketProduct getBasketPorductById(Long id){
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


    public List<BasketProduct> findAll() {
        return basketProductRepository.findAll();
    }


    public BasketProduct save(BasketProduct basketProduct) {
        return basketProductRepository.save(basketProduct);
    }


    public BasketProduct getById(long id) {

        Optional<BasketProduct> basketProductOptional = basketProductRepository.findById(id);

        return basketProductOptional.orElse(null);
    }


    public void delete(BasketProduct basketProduct) {
        basketProductRepository.delete(basketProduct);
    }


    public void corectSizeSellingProduct(BasketProductDto[] basketProductDtos) {

        for(BasketProductDto pr:basketProductDtos){

            double sizeTemp= productService.getProductById(pr.getProductId()).getSelling_size();

            productService.updateProductSallingSize(pr.getProductId(), sizeTemp, false);

            productService.updateProductSallingSize(pr.getProductId(), pr.getSize(), true);

            BasketProduct basketProduct = getBasketPorductById(pr.getId());
            assert basketProduct != null;
            basketProduct.setSize(pr.getSize());

            save(basketProduct);
        }
    }
}
