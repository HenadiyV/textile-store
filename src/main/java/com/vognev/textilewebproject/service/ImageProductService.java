package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.ImageProduct;
import com.vognev.textilewebproject.repository.ImageProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * textilewebproject_3  11/11/2021-9:43
 */
@Service
public class ImageProductService {
    @Autowired
    private ImageProductRepository imageProductRepository;


    public void updateShowcaseImageProduct(ImageProduct imageProduct){
        imageProductRepository.save(imageProduct);
    }


    void deleteImageProduct(ImageProduct imageProduct){
        imageProductRepository.delete(imageProduct);
    }


    void saveImageProduct(ImageProduct imageProduct){
        imageProductRepository.save(imageProduct);
    }


    ImageProduct getImageProductById(Long id){
       return imageProductRepository.getById(id);
    }


    List<ImageProduct> getExistFileName(String filename){

        List<ImageProduct> imageProduct =  imageProductRepository.getExistFileName(filename);

        return imageProduct;

    }

    String getImageFileName(Long id){
        ImageProduct imageProduct= imageProductRepository.productByImage(id);
        if(imageProduct!=null){
       return  imageProduct.getImgProduct();
        }
        return " ";
    }

}
