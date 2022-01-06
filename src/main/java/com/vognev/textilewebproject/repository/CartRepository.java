package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.Cart;
import com.vognev.textilewebproject.model.dto.CartDto;
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
}
