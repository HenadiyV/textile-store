package com.vognev.textilewebproject.model.util;

import com.vognev.textilewebproject.model.dto.CartDto;
import com.vognev.textilewebproject.model.dto.ProductDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * textilewebproject_3  27/11/2021-22:13
 */
public class JsonSimpleParser {

    public static CartDto parseStringToCartDto(String s){

        JSONParser parser= new JSONParser();

        try {
            JSONObject cartJsonObject= (JSONObject) parser.parse(s);
            Long  productId =(Long)cartJsonObject.get("productId");
            String productName = (String)cartJsonObject.get("productName");
            Long sellingPrice = (Long)cartJsonObject.get("sellingPrice");
            Long sizeProduct = (Long)cartJsonObject.get("sizeProduct");
            Long balance = (Long)cartJsonObject.get("balance");
            Long siz = (Long)cartJsonObject.get("siz");
            Long summ = (Long)cartJsonObject.get("summ");
            Long discountPrice = (Long)cartJsonObject.get("discountPrice");
            String infoCart = (String)cartJsonObject.get("infoCart");

            return   new CartDto(null,null,productId,productName,sellingPrice.doubleValue(),
                    sizeProduct.doubleValue(),balance.doubleValue(),siz.doubleValue(),summ.doubleValue(),discountPrice.doubleValue(),infoCart);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<CartDto>  parseArrayCartDto(String s){

        JSONParser parser= new JSONParser();

        try {
            List<CartDto> cartDtoList= new ArrayList<>();

             JSONObject cartJsonObject= (JSONObject) parser.parse(s);

            JSONArray cartArrayJson= (JSONArray) cartJsonObject.get("cart");

                for(Object it :cartArrayJson){

                    JSONObject itJsonObject= (JSONObject) it;

                    Long  productId =(Long)itJsonObject.get("productId");
                    String productName = (String)itJsonObject.get("productName");
                    Long sellingPrice = (Long)itJsonObject.get("sellingPrice");
                    Long sizeProduct = (Long)itJsonObject.get("sizeProduct");
                    Long balance = (Long)itJsonObject.get("balance");
                    Long siz = (Long)itJsonObject.get("siz");
                    Long summ = (Long)itJsonObject.get("summ");
                    Long discountPrice = (Long)itJsonObject.get("discountPrice");
                    String infoCart = (String)itJsonObject.get("infoCart");

                    CartDto cartDto= new CartDto(null,
                            null,
                            productId,
                            productName,
                            sellingPrice.doubleValue(),
                            sizeProduct.doubleValue(),
                            balance.doubleValue(),
                            siz.doubleValue(),
                            summ.doubleValue(),
                            discountPrice.doubleValue(),
                            infoCart
                    );
                    cartDtoList.add(cartDto);

                }
        return cartDtoList;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ProductDto parseProduct(String s){
        JSONParser parser= new JSONParser();
        CartDto cartDto= new CartDto();
        try {
            JSONObject cartJsonObject= (JSONObject) parser.parse(s);
          //cartDto=(CartDto)
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
