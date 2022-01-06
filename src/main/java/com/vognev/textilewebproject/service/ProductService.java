package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.ImageProduct;
import com.vognev.textilewebproject.model.Product;
import com.vognev.textilewebproject.model.dto.ImageProductDto;
import com.vognev.textilewebproject.model.dto.ProductDto;
import com.vognev.textilewebproject.model.dto.ProductWarehouseDto;
import com.vognev.textilewebproject.repository.ImageProductRepository;
import com.vognev.textilewebproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * textilewebproject_2  13/10/2021-19:55
 */
@Service
public class ProductService {

    @Autowired
    private  ProductRepository productRepository;


    @Autowired
    private  ImageProductRepository imageProductRepository;


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageProductService imageProductService;


    @Value("${upload.path}")
    private String uploadPath;


    public List<Product> findAll() {

        return productRepository.findAll();
    }


    public void saveProduct(Product product){

        productRepository.save(product);
    }


    public void deleteProduct(Product product){

        List<ImageProduct> imgProductList=product.getImageProducts();

        for(ImageProduct img: imgProductList){
            imageProductService.deleteImageProduct(img);
        }
        productRepository.delete(product);
    }


    public Integer colProduct(){

        return productRepository.findAll().size();
    }


   public ProductWarehouseDto warehouseDto(){

       Double size=0.0;
       Double purchace=0.0;
       Double selling=0.0;
       Double selling_size=0.0;
       Double remainder=0.0;
       Double sum_purchace=0.0;
       Double sum_selling=0.0;
       Double sum_remainder_purchace=0.0;
       Double profit=0.0;

        List<Product> productList = productRepository.findAll();

        for(Product pr : productList ){
            size+=pr.getSizeProduct();
            purchace+=pr.getPurchasePrice()*pr.getSizeProduct();
            selling+=pr.getSellingPrice()*pr.getSizeProduct();
            selling_size+=pr.getSelling_size();
            sum_selling+=pr.getSelling_size()*pr.getSellingPrice();
            sum_purchace+=pr.getSelling_size()*pr.getPurchasePrice();
        }
       remainder=size-selling_size;
       profit=sum_selling-sum_purchace;
       sum_remainder_purchace=purchace-sum_purchace;

       return new ProductWarehouseDto(
               size,
               purchace,
               selling,
               selling_size,
               sum_purchace,
               sum_selling,
               sum_remainder_purchace,
               remainder,profit);
   }


   public String getNameProduct(Long id){

        return productRepository.getById(id).getName();
   }


   Product getProductById(Long id){
        return productRepository.getById(id);
   }


   public ProductDto getProductDtoByProductId(Long id){

       ProductDto productDto= new ProductDto();

       productDto=getProductDto(id,true);

       return productDto;
   }


    public void updateProductSallingSize(Long product_id,Double balance,boolean operator){
        try{
            Product product = productRepository.getById(product_id);
if(operator) {
    product.setSelling_size(product.getSelling_size() + balance);
}else{
    product.setSelling_size(product.getSelling_size() - balance);
}
            productRepository.save(product);
        }catch(Exception ex){
            System.out.println("updateProductSallingSizeError ");
            ex.printStackTrace();
        }
    }


    public List<ProductDto> searchProduct(String name){

        List<ProductDto> productDtoList=productRepository.searchListProductDto(name.toUpperCase());

        return productDtoList;
    }


    public List<ImageProduct> getListImagesProduct(Long id){

            Product product=productRepository.getById(id);

            return product.getImageProducts();
    }


    public List<Product> getProductZbirList(Integer category_id) {

        return productRepository.productByCategoryId(category_id);
    }


    public ProductDto getProductToOrderDto(Long id){

        ProductDto productDto= new ProductDto();
        productDto=getProductDto(id,false);

        return productDto;
    }


    public List<ProductDto> getProductDtoList(){

        List<Product> productList= productRepository.findAll();

        List<ProductDto>productDtoList= new ArrayList<>();

        for(Product product : productList){

            productDtoList.add(getProductDto(product.getId(),false));
        }
        return productDtoList;
    }


    public void addProduct(Product product,MultipartFile[] files){

        Product productFromDb = productRepository.save(product);

            productFromDb.setImageProducts(saveImage(productFromDb,files));

            productRepository.save(productFromDb);
    }


    private ProductDto getProductDto(Long id,boolean full){

        Product product= productRepository.getById(id);

        Double balance= product.getSizeProduct()-product.getSelling_size();
String img=imageProductService.getImageFileName(product.getId());//product.getImageProducts().
        ProductDto productDto= new ProductDto(product.getId(),
                product.getName(),
                product.getColor(),
                product.getSizeProduct(),
                product.getSellingPrice(),
                product.isActive(),
                product.getDescription(),
                product.getDat(),
                product.getSelling_size(),
                product.getInfo(),
                img
        );
        //BeanUtils.copyProperties(product,productDto,"category","imageProducts");
        productDto.setCategory_id( product.getCategory().getId());
        if(full){
            productDto.setPurchasePrice(product.getPurchasePrice());
            productDto.setCategory( product.getCategory());
            productDto.setImageProducts(product.getImageProducts());
        }

        productDto.setProduct_balance(balance);
        productDto.setMetr(0.0);
        productDto.setBonus(0.0);
/*bonus: 0
color: "Черный"
id: 1
info: ""
metr: 0
name: "Name"
orderId: 1
product_balance: 19
sellingPrice: 110
sizeProduct: 50
summ: 0*/
        return productDto;
    }


    public void addNewImagesToProduct(Long id,MultipartFile[] files){

        Product product=productRepository.getById(id);

            for(ImageProduct imageProduct:product.getImageProducts()){
                imageProduct.setShowcase(false);
                imageProductService.saveImageProduct(imageProduct);
            }
        product.setImageProducts(saveImage(product,files));

        productRepository.save(product);
    }


    public void updateImageProduct(Long idProduct, Long idImage, MultipartFile file){

        Product product=productRepository.getById(idProduct);

        for(ImageProduct imageProduct:product.getImageProducts()){

            imageProduct.setShowcase(false);

            if(imageProduct.getId().equals(idImage)){
                try {
                   String resultFilename="";

                   int amount=searchFileName(file.getOriginalFilename());

                    if(amount==0) {

                        resultFilename = saveFile(file);
                    }else {

                        resultFilename = file.getOriginalFilename();
                    }
                    imageProduct.setImgProduct(resultFilename);
                   // imageProduct.setAmount(amount);
                    imageProduct.setShowcase(true);
                } catch (Exception e) {
                    System.out.println("updateImageProductError");
                    e.printStackTrace();
                }
            }
            imageProductService.saveImageProduct(imageProduct);
        }
        productRepository.save(product);

        addShowcaseImageProroductFromProduct(product);
    }


    public void deleteImageProduct(Long imageId){

        ImageProduct imageProduct =imageProductService.getImageProductById(imageId);

        Product product=imageProduct.getProduct();

        if(searchFileName(imageProduct.getImgProduct())==1){

            deleteImgFile(imageProduct.getImgProduct());
        }
        imageProductService.deleteImageProduct(imageProduct);

        addShowcaseImageProroductFromProduct(product);
    }


    private void addShowcaseImageProroductFromProduct(Product product){

        List<ImageProduct>imageProducts=new ArrayList<>(product.getImageProducts());

            long count=0;
                count = imageProducts.stream()
                .filter(ImageProduct::isShowcase)
                .count();

                if(count==0) {
                        for (ImageProduct imageProduct : imageProducts) {

                            if(!imageProduct.isShowcase()){

                            imageProduct.setShowcase(true);

                            imageProductService.saveImageProduct(imageProduct);

                            break;
                        }
                    }
                }
                if(count>1) {
                    boolean result=true;

                    for (ImageProduct imageProduct : imageProducts) {

                        if(imageProduct.isShowcase()&&result){
                            result=false;
                        }else{
                            imageProduct.setShowcase(false);

                            imageProductService.saveImageProduct(imageProduct);
                        }
                    }
                }
    }


    private List<ImageProduct> saveImage(Product product, MultipartFile[] files){

        List<ImageProduct> imgSet = new ArrayList<>();

        boolean showcase=true;

        for (MultipartFile file : files) {
            if (file != null && !file.getName().isEmpty()) {
                try {
                    ImageProduct imageProduct = new ImageProduct();

                    String resultFilename ="";

                    int amount =0;

                    amount = searchFileName(file.getOriginalFilename());

                    if(amount==0){

                        resultFilename = saveFile(file);

                    }else{

                        resultFilename = file.getOriginalFilename();
                    }
                    if(resultFilename!= null) {

                        imageProduct.setImgProduct(resultFilename);
                        imageProduct.setAmount(amount);
                        imageProduct.setInfo("");
                        imageProduct.setShowcase(showcase);
                        showcase = false;
                        imageProduct.setProduct(product);

                        imgSet.add(imageProduct);

                        imageProductService.saveImageProduct(imageProduct);
                    }
                } catch (Exception e) {
                    System.out.println("save file error");
                    e.printStackTrace();
                }
            }
        }
        return imgSet;
    }


    private String saveFile(MultipartFile file)  {
        try{
            if (file != null && !file.getName().isEmpty()) {

                File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
               uploadDir.mkdir();
            }
               // String uuidFile = UUID.randomUUID().toString(); uuidFile + "-" +
            String resultFilename =file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

                return resultFilename;
            }
        }catch (IOException ex){
            System.out.println("saveFileError");
            ex.printStackTrace();
        }
        return null;
    }


     private void deleteImgFile(String fileImgName){
         try {
             File f = new File(uploadPath+"/"+fileImgName);
            // System.out.println(f.getCanonicalPath());
             //URL xmlpath = this.getClass().getClassLoader().getResource(fileImgName);
             //System.out.println(xmlpath);
            Files.delete(Paths.get(f.getCanonicalPath()));
         } catch (IOException x) {
             System.err.println(x);
         }
     }


     private int searchFileName(String name){

        List<ImageProduct>imageProducts= imageProductService.getExistFileName(name);

        int amount =0;

        if(imageProducts!=null){
            amount=imageProducts.size();
        }
         return amount;
     }
}