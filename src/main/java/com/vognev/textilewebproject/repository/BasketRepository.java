package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * textilewebproject_3  29/12/2021-8:50
 */
public interface BasketRepository extends JpaRepository<Basket,Long> {

    @Query("from Basket bs where bs.token like :token")
    Basket searchBasketToToken(@Param("token")String token);

    @Query("from Basket bs where bs.dat_clear =:dat1")
    List<Basket> searchBasketToClearDate(@Param("dat1")Date dat1);


    @Query("from Basket bs where bs.id =:id")
    boolean foundBasketId(@Param("id")Long id);
}
