package com.vognev.textilewebproject.repository;


import com.vognev.textilewebproject.dto.ProductStatistic;
import com.vognev.textilewebproject.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * textilewebproject_2  30/09/2021-14:06
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("from Product p where  p.name like :name% ")
    Page<Product>findByName(@Param("name")String name,Pageable pageable);

    @Query("from Product p where p.name like :name% ")
    List<Product> searchProduct(@Param("name")String name);


    @Query("from Product p where p.category.id =:id")
    Page<Product> productByCategoryId(@Param("id")int id, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    @Query("from Product p order by p.id desc")
    List<Product> getAllProduct();

    @Query("select count(pr) from Product pr")
    Integer getCountProducts();

    @Query("select Sum(pr.selling_size*pr.sellingPrice) from Product pr ")
    Double getSelling();

    @Query("select Sum(pr.sizeProduct*pr. purchasePrice) from Product pr ")
    Double getPurchase();

    @Query("select  new com.vognev.textilewebproject.dto.ProductStatistic(pr.dat,(pr.purchasePrice*pr.sizeProduct),pr.id) from Product pr order by pr.dat")
    List<ProductStatistic> getStatisticProduct();


    @Query("from Product pr order by pr.id")
    List<Product> getListProduct();
}
