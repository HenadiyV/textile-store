package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.model.dto.CartDto;
import com.vognev.textilewebproject.model.util.OrderSumm;
import com.vognev.textilewebproject.model.dto.UserProductDto;
import com.vognev.textilewebproject.repository.CartDeletedRepository;
import com.vognev.textilewebproject.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipFile;

/**
 * textilewebproject_2  24/10/2021-19:11
 */
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    @Autowired
    private ProductService productService;


    @Autowired
    private OrderService orderService;


    @Autowired
    private CategoryService categoryService;


    @Autowired
    private CartDeletedRepository cartDeletedRepository;

    @Autowired
    private ImageProductService imageProductService;


    public CartDto getCartDto(Long id){

        Cart cart= getById(id);

        if(cart!=null) {
            Product product = productService.getProductById(cart.getProduct_id());

            ImageProduct imageProduct = imageProductService.getImageProductById(product.getId());

            Double summ = (cart.getSiz() * cart.getSalePrice() - cart.getDiscount_price());

            Double balance = product.getSizeProduct() - product.getSelling_size();

            return new CartDto(id,
                    cart.getOrder().getId(),
                    cart.getProduct_id(),
                    cart.getNameProduct(),
                    cart.getSalePrice(),
                    product.getSizeProduct(),
                    balance,
                    cart.getSiz(),
                    summ,
                    cart.getDiscount_price(),
                    cart.getInfo(),
                    imageProduct.getImgProduct()
                    );
        }else{
            return null;
        }
    }


    public void createCartByOrder(Order order, List<CartDto> carts){
        try{
            for(CartDto crt:carts){

                productService.updateProductSallingSize(crt.getProductId(),crt.getSiz(),true);

                Cart cart= new Cart(
                        crt.getProductId(),
                        crt.getSellingPrice(),
                        crt.getProductName(),
                        crt.getSiz(),
                        crt.getDiscountPrice(),
                        crt.getInfoCart(),
                        order.getDat_create(),
                        order);

               save(cart);
            }
        }catch(Exception ex){
            System.out.println("createCartByOrderErr");
            ex.printStackTrace();
        }
    }


    List<CartDto> getCartDtoListFromOrder(Long orderId, List<Cart> carts){

        List<CartDto>cartDtos= new ArrayList<>();

        double summ=0;

        for (Cart cr:carts){

            Product product =productService.getProductById(cr.getProduct_id());

            ImageProduct imageProduct = imageProductService.productByImage(product.getId());
            CartDto cartDto = new CartDto(
                    cr.getId(),
                    orderId,
                    product.getId(),
                    product.getName(),
                    product.getSellingPrice(),
                    product.getSizeProduct(),
                    product.getSelling_size(),
                    cr.getSiz(),
                    cr.getSiz()*cr.getSalePrice()-cr.getDiscount_price(),
                    cr.getDiscount_price(),
                    cr.getInfo(),
            imageProduct.getImgProduct()
            );
            cartDtos.add(cartDto);

            summ+=cr.getSiz()*cr.getSalePrice()-cr.getDiscount_price();
        }

        OrderSumm.setOrderSumm(summ);

        return cartDtos;
    }


    public void addCartToOrder(CartDto[] carts){
    try{
        Date dat = new Date();

        for (CartDto cart1 : carts) {

            Order order = orderService.getOrderById(cart1.getOrderId());

            productService.updateProductSallingSize(cart1.getProductId(), cart1.getSiz(),true);

            Cart cart = new Cart(
                    cart1.getProductId(),
                    cart1.getSellingPrice(),
                    cart1.getProductName(),
                    cart1.getSiz(),
                    cart1.getDiscountPrice(),
                    cart1.getInfoCart(),
                    dat,
                    order);

            save(cart);
        }
       }catch(Exception ex){
            System.out.println("addCartToOrderErr");
            ex.printStackTrace();
        }
    }


    public void updateCart(Long cartId,
                           Long product_id,
                           Double salePrice,
                           Double siz,
                           Double discount_price,
                           String product_name,
                           String info_cart,
                           Double balance_product
    ){
        Cart cartDB= cartRepository.getById(cartId);

        if(siz>0&&cartDB.getSiz()!=siz){

                productService.updateProductSallingSize(product_id,cartDB.getSiz(),false);

                productService.updateProductSallingSize(product_id,siz,true);

        }
        cartDB.setProduct_id(product_id);
        cartDB.setSiz(siz);
        cartDB.setDiscount_price(discount_price);
        cartDB.setSalePrice(salePrice);
        cartDB.setNameProduct(product_name);
        cartDB.setInfo(info_cart);

       save(cartDB);
    }


    double summOrder(List<Cart> carts){

        double summ=0.0;

        for(Cart crt:carts){

            summ+=crt.getSiz()*crt.getSalePrice();
        }
        return summ;
    }


    void deleteListCart(List<Cart> carts){

        Date dat = new Date();

        try{
            for(Cart crt:carts){
                deleteCart(crt);
            }
        }catch(Exception ex){
            System.out.println("deleteListCartErr");
            ex.printStackTrace();
        }
    }


    public void deleteCart(Cart cart){

        Date dat = new Date();

        try{

        CartDeleted cartDeleted= new CartDeleted(
                cart.getOrder().getUser().getId(),
                cart.getOrder().getId(),
                cart.getId(),
                cart.getProduct_id(),
                cart.getNameProduct(),
                cart.getSiz(),
                cart.getSiz()*cart.getSalePrice(),
                cart.getDiscount_price(),
                cart.getDat(),
                dat,
                cart.getInfo()
        );

        productService.updateProductSallingSize(cart.getProduct_id(),cart.getSiz(),false);

        cartDeletedRepository.save(cartDeleted);

        cartRepository.delete(cart);
        }catch(Exception ex){
            System.out.println("deleteCartErr");
            ex.printStackTrace();
        }
    }


    public List<UserProductDto> getUserProduct(Long porductId){

        List<Cart>carts=cartRepository.getCartsByProduct_id(porductId);

        List<UserProductDto>userProductDtos= new ArrayList<>();

        Product product = productService.getProductById(porductId);

        for(Cart crt:carts){

            Order order=crt.getOrder();

            String address=order.getAddressUser().getCity()+" | "+
                    order.getAddressUser().getAddress()+" | "+order.getAddressUser().getPostCode();

            UserProductDto userProductDto = new UserProductDto(
                    order.getUser().getId(),
                    order.getUser().getName(),
                    order.getUser().getUsername(),
                    order.getPhoneUser().getPhone(),
                    address,order.getPostOfficeUser().getPostOffice(),
                    crt.getId(),
                    product.getCategory().getName(),
                    crt.getProduct_id(),
                    crt.getNameProduct(),
                    product.getColor(),
                    product.getSizeProduct(),
                    product.getPurchasePrice(),
                    crt.getSalePrice(),
                    product.getDescription(),
                    crt.getDat(),
                    crt.getSiz(),
                    crt.getInfo());

            userProductDtos.add(userProductDto);
        }
        return userProductDtos;
    }


    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }


    public List<Cart> findAll() {
        return cartRepository.findAll();
    }


    public Cart getById(long cartId) {
        try{

            Optional<Cart> cartOpt=cartRepository.findById(cartId);

            return cartOpt.orElse(null);
        }catch(Exception ex){
            System.out.println("Error-getCartById 315");
            ex.printStackTrace();
            return null;
        }
    }
}
//====================CartService  ===============
