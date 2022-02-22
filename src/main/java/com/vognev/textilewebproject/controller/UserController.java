package com.vognev.textilewebproject.controller;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.service.AddressService;
import com.vognev.textilewebproject.service.PhoneService;
import com.vognev.textilewebproject.service.PostOfficeService;
import com.vognev.textilewebproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * textilewebproject  18/09/2021-20:13
 */
@Controller
//@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
@RequestMapping("/user")
//@Transactional
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
        model.addAttribute("user",userService.getUser(user.getId()));
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

MyUser user = userService.getUser(myUser.getId());
        boolean isConfirmUsername = ObjectUtils.isEmpty(username);

        if(isConfirmUsername){

            model.addAttribute("usernameError","Поле не може бути пустим!");
            model.addAttribute("user",user);
            model.addAttribute("addresess",addressService.getAddressByUserId(user.getId()));
            model.addAttribute("phones",phoneService.getPhoneUserListFromUserId(user.getId()));
            model.addAttribute("postOffices",postOfficeService.getPostOfficeUserListFromUserId(user.getId()));
            return "/profile";
        }

        if(!username.equals(user.getUsername())&&userService.searchUser(username).size()>0){
            model.addAttribute("usernameError","Нік зайнятий!");//Задайте Нік
            model.addAttribute("user",user);
            model.addAttribute("addresess",addressService.getAddressByUserId(user.getId()));
            model.addAttribute("phones",phoneService.getPhoneUserListFromUserId(user.getId()));
            model.addAttribute("postOffices",postOfficeService.getPostOfficeUserListFromUserId(user.getId()));
            return "/profile";
        }

        boolean isEmail = ObjectUtils.isEmpty(email);
       // if(!isEmail&&!email.equals(user.getEmail())){}
        if(!isEmail&&!email.equals(user.getEmail()) && userService.searchEmail(email)!=null){
            model.addAttribute("emailError","Email зайнятий!");
            model.addAttribute("user",user);
            model.addAttribute("addresess",addressService.getAddressByUserId(user.getId()));
            model.addAttribute("phones",phoneService.getPhoneUserListFromUserId(user.getId()));
            model.addAttribute("postOffices",postOfficeService.getPostOfficeUserListFromUserId(user.getId()));
            return "/profile";
        }
       userService.updateProfile(username,name,password,email,info,user );

        return"redirect:/user/profile";
    }


    @PostMapping("profile-address")
    public String addAddress(
            @AuthenticationPrincipal MyUser user,
            @RequestParam String region,
            @RequestParam String district,
            @RequestParam String city,
            @RequestParam String address,
            @RequestParam String postCode,
            @RequestParam String info,
            @RequestParam boolean active

    ){
        addressService.addAddressFromUser(region,district,city,address,postCode,info,active,user);

        return"redirect:/user/profile";
    }

    @PostMapping("update-address")
    public String updateAddress(
            @RequestParam Long id,
            @RequestParam String region,
            @RequestParam String district,
            @RequestParam String city,
            @RequestParam String address,
            @RequestParam String postCode,
            @RequestParam String info,
            @RequestParam(name="active", required = false) boolean active
            ){
        addressService.updateAddress(id,region,district,city,address,postCode,info,active);

        return"redirect:/user/profile";
    }

    @PostMapping("profile-phone")
    public String addPhoneToUser(
            @AuthenticationPrincipal MyUser user,
            @RequestParam String phone,
            @RequestParam String info,
            @RequestParam boolean active

    ){
        if(phoneService.getNumberPhone(phone)==null){

        phoneService.addPhoneFromUser(phone,info,active,user);

        }

        return"redirect:/user/profile";///
    }


    @PostMapping("/update-phone")
    public String updatePhone(
            @RequestParam("id")Long id,
            @RequestParam("phone")String phone,
            @RequestParam("info")String info,
            @RequestParam(name="active", required = false)boolean active
            ){

        phoneService.updateProne(id,phone,info,active);

        return"redirect:/user/profile";
    }


    @PostMapping("profile-postOffice")
    public String addPostOfficeToUser(
            @AuthenticationPrincipal MyUser user,
            @RequestParam String postOffice,
            @RequestParam String info,
            @RequestParam boolean active
    ){
        postOfficeService.addPostOfficeFromUser(postOffice,info,active,user);

        return"redirect:/user/profile";
    }


    @PostMapping("/update-postOffice")
    public String updatePostOffice(
           @RequestParam Long id,
           @RequestParam String postOffice,
           @RequestParam String info,
           @RequestParam(name="active", required = false) boolean active
            ){
        postOfficeService.updatePostOffice(id,postOffice,info,active);

        return"redirect:/user/profile";
    }
}