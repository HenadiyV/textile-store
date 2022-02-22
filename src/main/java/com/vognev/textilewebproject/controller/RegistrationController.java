package com.vognev.textilewebproject.controller;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.dto.CaptchaResponceDto;
import com.vognev.textilewebproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

/**
 * textilewebproject  18/09/2021-10:01
 */
@Controller
public class RegistrationController {

    private final static String CAPTCHA_URL="https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService userService;


    @Value("${recaptcha.secret}")
    private String secret;


    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/registration")
    public String registration(){
        return"registration";
    }


    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2")String passwordConfirm,
            @RequestParam("g-recaptcha-response")String captchaResponce,
            @Valid MyUser myUser,
            BindingResult bindingResult,
            Model model
    ) {
        String url=String.format(CAPTCHA_URL,secret,captchaResponce);

        CaptchaResponceDto response= restTemplate.postForObject(url,Collections.emptyList(), CaptchaResponceDto.class);

        if(!response.isSuccess()){
            model.addAttribute("captchaError","Fill captcha");
        }

        boolean isConfirm = ObjectUtils.isEmpty(passwordConfirm);

        if(isConfirm){

            model.addAttribute("password2Error","Паролі різні.");//Passwords are different
        }

        if(myUser.getPassword()!=null &&!myUser.getPassword().equals(passwordConfirm)){
        //Password confirmation cannot be empty
            model.addAttribute("passwordError","Поле підтвердження пароля не може бути порожнім.");
        }

        if(isConfirm||bindingResult.hasErrors()||!response.isSuccess()){

            Map<String,String> errors=ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute("user",myUser);

            return "registration";
        }
        if (!userService.addUser(myUser)) {

            model.addAttribute("usernameError", "Користувач існує!");

            return "registration";
        }

        return "redirect:/login";
    }


    @GetMapping("/activate/{code}")
    public String activate(
            Model model,
            @PathVariable String code
    ){
        boolean isActivated = userService.activateUser(code);

        if(isActivated){

            model.addAttribute("messageType","success");
            model.addAttribute("message","Користувача успішно активовано ");//User successfully activated
        }else{

            model.addAttribute("messageType","danger");
            model.addAttribute("message","Код активації не знайдено! ");//Activation code is not found!

        }
        return "login";
    }

}
