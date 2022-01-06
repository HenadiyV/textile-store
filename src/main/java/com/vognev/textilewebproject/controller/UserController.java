package com.vognev.textilewebproject.controller;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.Role;
import com.vognev.textilewebproject.repository.MyUserRepository;
import com.vognev.textilewebproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("profile")
    public String getProfile(
            Model model,
            @AuthenticationPrincipal MyUser user
    ){
        model.addAttribute("username",user.getUsername());
        model.addAttribute("email",user.getUsername());

        return "profile";
    }

    
    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal MyUser user,
            @RequestParam String password,
            @RequestParam String email
    ){
        userService.updateProfile(user,password,email);

        return"redirect:/user/profile";
    }
}