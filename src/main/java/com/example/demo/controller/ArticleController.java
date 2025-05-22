package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articles", service.getAll());
        return "index";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("article", new Article());
        return "form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Article article) {
        service.save(article);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("article", service.get(id));
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }
}
