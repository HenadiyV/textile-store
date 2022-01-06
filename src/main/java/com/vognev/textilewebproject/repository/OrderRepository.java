package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.Cart;
import com.vognev.textilewebproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * textilewebproject_2  16/10/2021-9:37
 */
public interface OrderRepository extends JpaRepository<Order,Long> {

}
