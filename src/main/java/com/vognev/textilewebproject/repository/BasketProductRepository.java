package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * textilewebproject_3  29/12/2021-8:52
 */
public interface BasketProductRepository extends JpaRepository<BasketProduct,Long> {

    @Query("from BasketProduct bp where bp.basket.id =:id")
    List<BasketProduct> getBasketProductList(@Param("id")Long id);
}
