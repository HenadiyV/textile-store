package com.vognev.textilewebproject.controller;

import com.vognev.textilewebproject.model.Category;
import com.vognev.textilewebproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

/**
 * textilewebproject_3  10/11/2021-21:45
 */
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/category")
    public String allCategory(Model model){

        model.addAttribute("categoryList", categoryService.findAll());

        return "admin-category";
    }


    @PostMapping("/category")
    public String addCategory(
            @Valid Category category,
            BindingResult bindingResult,
            Model model
    ){
        if (bindingResult.hasErrors()) {

            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("category", category);
        } else {

            model.addAttribute("category", null);

            categoryService.save(category);
        }
        model.addAttribute("categoryList", categoryService.findAll());

        return "admin-category";
    }


    @GetMapping("/category/{category}")
    public String editCategory(
            @PathVariable Category category,
            Model model
    ){
        model.addAttribute("category", category);
        model.addAttribute("categoryList", categoryService.findAll());

        return "admin-category";
    }


    @PostMapping("/category/{category}")
    public String updateCategory(
            @Valid Category category,
            BindingResult bindingResult,
            Model model
    ){
        if (bindingResult.hasErrors()) {

            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("category", category);
        } else {

            model.addAttribute("category", null);

            categoryService.save(category);
        }
        model.addAttribute("categoryList", categoryService.findAll());

        return "admin-category";
    }

}
