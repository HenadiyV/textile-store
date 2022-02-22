package com.vognev.textilewebproject.controller;


import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


/**
 * textilewebproject_2  29/09/2021-16:46
 */
@Controller

@RequestMapping("/admin")
@CrossOrigin
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostOfficeService postOfficeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public String adminPage(Model model){

        model.addAttribute("col_product",productService.colProduct());
        model.addAttribute("col_user",userService.colUsers());
        model.addAttribute("sellingSumm",productService.sellingSumm());
        model.addAttribute("purchaseSumm",productService.getPurchaseSumm());


        return "admin";
    }

    @GetMapping("/user")
    public String userList( Model model){

        model.addAttribute("users", userService.partMyUserList());

        return "admin-user";
    }


    @GetMapping("/user/{user}")
    public String userEditFrorm(
            @PathVariable MyUser user,
            Model model
    ){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }


    @GetMapping("/add-user")
    public String viewAddUser( Model model){

        model.addAttribute("roles", Role.values());

        return "admin-user";
    }


    @PostMapping("/add-user")
    public String addUser(
            @Valid MyUser user,
            @RequestParam(name="info", required=false,defaultValue = " ")String info,
            @RequestParam(name="phone", required=false,defaultValue = " ")String phone,
            @RequestParam String region,
            @RequestParam String district ,
            @RequestParam(name="city", required=false,defaultValue = " ")String city,
            @RequestParam(name="address", required=false,defaultValue = " ")String address,
            @RequestParam(name="postCode", required=false,defaultValue = " ")String postCode,
            @RequestParam(name="postOffice", required=false,defaultValue = " ")String postOffice,
            Model model
    ){
        Map<String,String> errorM= userService.addUserFromAdmin(user,info,phone,region,district,city,address,postCode,postOffice);

        if(errorM.size()==0) {
            model.addAttribute("user", null);
        }else{
            model.mergeAttributes(errorM);
            model.addAttribute("user", user);
            model.addAttribute("userIsPresent", "User is present here ");
            model.addAttribute("phone", phone);
            model.addAttribute("region", region);
            model.addAttribute("district", district);
            model.addAttribute("city", city);
            model.addAttribute("address", address);
            model.addAttribute("postCode", postCode);
            model.addAttribute("postOffice", postOffice);
        }
        model.addAttribute("users", userService.findAll());

        return "admin-user";
    }


    @GetMapping("/users")
    public String allUser(Model model){

        model.addAttribute("users", userService.findAll());

        return "admin-user";
    }


    @GetMapping("/user-update/{user}")
    public String updateUser(
            @PathVariable MyUser user,
            Model model
    ){
        model.addAttribute("userUp", user);
        model.addAttribute("users", userService.findAll());

        return "admin-user";
    }


    @PostMapping("/user-update/")
    public String editUser(
            @RequestParam("id") MyUser user,
            @RequestParam("username")String username,
                    @RequestParam("name") String name,
                    @RequestParam("email") String email,
                    @RequestParam("info") String info,
                    @RequestParam("rating") int rating,
                    @RequestParam(name="active", required=false, defaultValue="0")boolean active,
            Model model
    ){
         Map<String,String> err=  userService.updateMyUser(user,username, name, email,rating,info, active);

        if(err.size()==0) {
            model.addAttribute("user", null);
            model.addAttribute("name", null);
            model.addAttribute("email", null);
            model.addAttribute("info", null);
            model.addAttribute("rating", null);
            model.addAttribute("active", null);
        }else{
            model.mergeAttributes(err);
            model.addAttribute("user", user);
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("info", info);
            model.addAttribute("rating", rating);
            model.addAttribute("active", active);
        }

        model.addAttribute("userUp", user);
        model.addAttribute("users", userService.findAll());

        return "admin-user";
    }


    @PostMapping("add-phone")
    public String addPhone(Model model){

        model.addAttribute("users", userService.findAll());

        return "admin-user";
    }


    @PostMapping("add-post-office")
    public String addPostOffice(Model model){

        model.addAttribute("users", userService.findAll());

        return "admin-user";
    }


    @PostMapping("add-address")
    public String addAddress(Model model){

        model.addAttribute("users", userService.findAll());

        return "admin-user";
    }

}
