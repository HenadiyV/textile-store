package com.vognev.textilewebproject.repository;


import com.vognev.textilewebproject.model.Product;
import com.vognev.textilewebproject.model.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * textilewebproject_2  30/09/2021-14:06
 */
public interface ProductRepository extends JpaRepository<Product, Long> {



    @Query("from Product p where p.name like :name% ")
    List<Product> searchProduct(@Param("name")String name);


    @Query("from Product p where p.category.id =:id")
    Page<Product> productByCategoryId(@Param("id")int id, Pageable pageable);

    Page<Product> findAll(Pageable pageable);
    @Query("from Product p order by p.id desc")
    List<Product> getAllProduct();

}
