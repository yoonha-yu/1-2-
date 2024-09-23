package com.std.TEST2.Article;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/article")
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model){
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    @GetMapping("/create")
    public String createForm(Model model, ArticleForm articleForm){
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("article", new Article());
        return "article_form";
    }

    @PostMapping ("/create")
    public String createArticle(Article article){
        articleService.save(article);
        return "redirect:/article/list";

    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id")  Integer id){
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }
}
