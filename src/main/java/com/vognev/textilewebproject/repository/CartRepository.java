package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.dto.ProductStatistic;
import com.vognev.textilewebproject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * textilewebproject_2  16/10/2021-9:40
 */
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query(" from Cart cr where cr.product_id =:id ")
    List<Cart> getCartsByProduct_id(@Param("id")Long id);

    @Query("select  new com.vognev.textilewebproject.dto.ProductStatistic(crt.dat,(crt.salePrice*crt.siz-crt.discount_price),crt.id) from Cart crt order by crt.dat")
    List<ProductStatistic> getStatisticCart();

}
