package com.vognev.textilewebproject.controller;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.Role;
import com.vognev.textilewebproject.model.dto.AddressUserDto;
import com.vognev.textilewebproject.model.dto.PhoneUserDto;
import com.vognev.textilewebproject.model.dto.PostOfficeUserDto;
import com.vognev.textilewebproject.repository.MyUserRepository;
import com.vognev.textilewebproject.service.AddressService;
import com.vognev.textilewebproject.service.PhoneService;
import com.vognev.textilewebproject.service.PostOfficeService;
import com.vognev.textilewebproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * textilewebproject  18/09/2021-20:13
 */
@Controller
@RequestMapping("/user")

public class UserController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private PostOfficeService postOfficeService;

    @GetMapping("profile")
    public String getProfile(
            Model model,
            @AuthenticationPrincipal MyUser user
    ){
        model.addAttribute("user",user);
       // model.addAttribute("username",user.getUsername());
        //model.addAttribute("email",user.getEmail());
        model.addAttribute("addresess",addressService.getAddressByUserId(user.getId()));
        model.addAttribute("phones",phoneService.getPhoneUserListFromUserId(user.getId()));
        model.addAttribute("postOffices",postOfficeService.getPostOfficeUserListFromUserId(user.getId()));

        return "profile";
    }

    
    @PostMapping("profile-user")
    public String updateUser(
            @AuthenticationPrincipal MyUser myUser,
            @RequestParam String username,
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String email,
            @RequestParam String info,

            Model model
    ){
        boolean isConfirm = ObjectUtils.isEmpty(confirmPassword);

        //userService.updateProfile(user,password,email);

        return"redirect:/user/profile";
    }

    @PostMapping("profile-address")
    public String updateAddress(
            @AuthenticationPrincipal MyUser user,
            @RequestParam String city,
            @RequestParam String address,
            @RequestParam String postCode,
            @RequestParam String info,
            @RequestParam boolean active

    ){
        //userService.updateProfile(city, address, postCode, info, active);
        addressService.addAddressFromUser(city,address,postCode,info,active,user);
        return"redirect:/user/profile";
    }

    @PostMapping("update-address")
    public String updateAddress(
            @RequestParam Long id,
            @RequestParam String city,
            @RequestParam String address,
            @RequestParam String postCode,
            @RequestParam String info,
            @RequestParam boolean active
            ){
        System.out.println(address);
        return"redirect:/user/profile";
    }

    @PostMapping("profile-phone")
    public String addPhoneToUser(
            @AuthenticationPrincipal MyUser user,
            @RequestParam String phone,
            @RequestParam String info,
            @RequestParam boolean active
    ){
        //userService.updateProfile(user,phone,info,active);

        phoneService.addPhoneFromUser(phone,info,active,user);
        return"redirect:/user/profile";
    }

    @PostMapping("/update-phone")
    public String updatePhone(
            @RequestParam("id")Long id,
            @RequestParam("phone")String phone,
            @RequestParam("info")String info,
            @RequestParam("active")boolean active
            ){
        /*@RequestBody phone
active
info
id*/
        System.out.println(phone);
        return"redirect:/user/profile";
    }

    @PostMapping("profile-postOffice")
    public String addPostOfficeToUser(
            @AuthenticationPrincipal MyUser user,
            @RequestParam String postOffice,
            @RequestParam String info,
            @RequestParam boolean active
    ){
        //userService.updateProfile(user,postOffice,info,active);

        postOfficeService.addPostOfficeFromUser(postOffice,info,active,user);
        return"redirect:/user/profile";
    }

    @PostMapping("/update-postOffice")
    public String updatePostOffice(
           @RequestParam long id,
           @RequestParam String postOffice,
           @RequestParam String info,
           @RequestParam boolean active
            ){
        /*postOffice
active
info
id*/
        System.out.println(postOffice);
        return"redirect:/user/profile";
    }
}