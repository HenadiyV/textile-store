package com.vognev.textilewebproject.repository;


import com.vognev.textilewebproject.model.Product;
import com.vognev.textilewebproject.model.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * textilewebproject_2  30/09/2021-14:06
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select new com.vognev.textilewebproject.model.dto.ProductDto(p.id,p.name,p.color,p.sizeProduct," +
            "p.sellingPrice,p.active,p.description,p.dat,p.selling_size,p.info)" +
            "from Product p where p.name like :name% ")
    List<ProductDto> searchListProductDto(@Param("name")String name);

    @Query("from Product p where p.category.id =:id")
    List<Product> productByCategoryId(@Param("id")int id);

}
