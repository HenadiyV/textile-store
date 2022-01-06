package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * textilewebproject_2  30/09/2021-14:06
 */
public interface ImageProductRepository extends JpaRepository<ImageProduct , Long> {
    @Query("from ImageProduct img where img.imgProduct like :name")
    List<ImageProduct> getExistFileName(@Param("name")String name);

    @Query("from ImageProduct img where img.product.id =:id and img.showcase ='1'")
    List<ImageProduct> getShowcaseTrue(@Param("id")Long id);

    @Query("from ImageProduct ip where ip.product.id =:id and ip.showcase ='1'")
    ImageProduct productByImage(@Param("id")Long id);
}
