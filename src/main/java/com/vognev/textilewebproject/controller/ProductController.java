package com.vognev.textilewebproject.controller;

import com.vognev.textilewebproject.dto.TagDto;
import com.vognev.textilewebproject.model.Category;
import com.vognev.textilewebproject.model.Product;
import com.vognev.textilewebproject.dto.ProductDto;
import com.vognev.textilewebproject.model.Tag;
import com.vognev.textilewebproject.service.TagService;
import com.vognev.textilewebproject.util.DateHelper;
import com.vognev.textilewebproject.service.CategoryService;
import com.vognev.textilewebproject.service.ImageProductService;
import com.vognev.textilewebproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * textilewebproject_3  10/11/2021-21:34
 */
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageProductService imageProductService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/product")
    public String allProducts(
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ){

        model.addAttribute("page", productService.getPagerAllProduct(pageable));
        //productService.createPriceDollar();
        model.addAttribute("url","/product");
        return "admin-product";
    }


    @GetMapping("/add-product")
    public String addProductGet(Model model){

        model.addAttribute("categoryList",categoryService.findAll());

        model.addAttribute("tagList",tagService.getListTagDto());

        model.addAttribute("imgUrl","/product/add-product/0");

        return "parts/productEdit";
    }


    @PostMapping("/add-product")
    public String addProductPost(
            @Valid Product product,
            BindingResult bindingResult,
            @RequestParam String dat_dispatch,
            @RequestParam (name="tagStr") String tagStr,
            @RequestParam(value = ("files"),required = false) MultipartFile[] files,
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable
    ) throws IOException {

        if(bindingResult.hasErrors()){

            model.addAttribute("categoryList",categoryService.findAll());

            Map<String,String> errors=ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute("product",product);
            model.addAttribute("tagList",tagService.findAll());
            model.addAttribute("imgUrl","/product/add-product/0");///product/add-product/0
            return "parts/productEdit";
        }
        Date dat= DateHelper.convertStringToDate(dat_dispatch);

        product.setDat(dat);

       productService.addProduct(product,files,tagStr);

        model.addAttribute("productList", productService.getProductListAllPageable(pageable));

        return "redirect:/product";
    }


    @GetMapping("/edit-product/{product}")
    public String editProduct(
            @PathVariable Long product,
            Model model
    ){
        Product pr= productService.getProductById(product);//getProductDtoByProductId(product);

        model.addAttribute("product",pr);
        model.addAttribute("categoryList",categoryService.findAll());
        model.addAttribute("tagList",tagService.getProductTag(pr.getTags()));

        return "parts/productEdit";
    }


    @PostMapping("/edit-product/{id_product}")
    public String updateProduct(
            @PathVariable(name="id_product") Product product,
            @RequestParam(name="name") String name,
            @RequestParam(name="color") String color,
            @RequestParam(name="category") Category category,
            @RequestParam(name="dat_dispatch") String dat,
            @RequestParam(name="active") boolean active,
            @RequestParam(name="purchasePrice") double purchasePrice,
            @RequestParam(name="purchasePriceDollar") double purchasePriceDollar,
            @RequestParam(name="sellingPrice") double sellingPrice,
            @RequestParam(name="sizeProduct") double sizeProduct,
            @RequestParam(name="selling_size") double selling_size,
            @RequestParam(name="description") String description,
            @RequestParam(name="info") String info,
            @RequestParam (name="tagStr") String tagStr,

            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable
    ){
        Date dat_product= com.vognev.textilewebproject.util.DateHelper.convertStringToDate(dat);

        Product pr=product;
        pr.setName(name);
        pr.setColor(color);
        pr.setCategory(category);
        pr.setSellingPrice(sellingPrice);
        pr.setSizeProduct(sizeProduct);
        pr.setPurchasePrice(purchasePrice);
        pr.setPurchasePriceDollar(purchasePriceDollar);
        pr.setDat(dat_product);
        pr.setActive(active);
        pr.setSelling_size(selling_size);
        pr.setDescription(description);
        pr.setInfo(info);

       productService.addProduct(product,null,tagStr);
        model.addAttribute("page", productService.getPagerAllProduct(pageable));

        model.addAttribute("url","/product");
        model.addAttribute("tagList",tagService.findAll());



        return "admin-product";
    }


    @PostMapping("/product/update-img")
    public String editImageProduct(
            @RequestParam("id")Long id,
            @RequestParam("imageId")Long imageId,
            @RequestParam("files") MultipartFile files,
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable
    ) throws IOException {

        productService.updateImageProduct(id,imageId,files);

        model.addAttribute("productList", productService.getProductListAllPageable(pageable));

        return "redirect:/product";
    }


    @RequestMapping(value="/product/add-img/{id}", method= RequestMethod.POST)
    public String addImageProduct(
            @RequestParam(name="id")Long id,
            @RequestParam(name="files") MultipartFile[] files,
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable
    ) throws IOException {

        productService.addNewImagesToProduct(id,files);

        model.addAttribute("productList", productService.getProductListAllPageable(pageable));

        return "redirect:/product";
    }


    @RequestMapping(value="/product/delete-img/{id}", method= RequestMethod.GET)
            public String deleteImage(
                    @PathVariable(name="id")Long imageId,
                    Model model,
                    @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable
    ){
        productService.deleteImageProduct(imageId);

        model.addAttribute( "productList", productService.getProductListAllPageable(pageable));

        return "redirect:/product";
    }

    @GetMapping("/product/search")
    public String searchProduct(
            @RequestParam String product_name,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ){

        model.addAttribute("page", productService.getPagerSearchProduct(product_name,pageable));

        model.addAttribute("url","/product");
        return "admin-product";
    }
}