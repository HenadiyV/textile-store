package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * textilewebproject_3  29/12/2021-8:50
 */
public interface BasketRepository extends JpaRepository<Basket,Long> {
    @Query("from Basket bs where bs.token like :token")
    Basket searchBasketToToken(@Param("token")String token);
}
