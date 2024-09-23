package com.std.TEST2.Article;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getList(){
        return this.articleRepository.findAll();
    }
    public Article getArticle(Integer id){
        Optional<Article> article = this.articleRepository.findById(id);
        if(article.isEmpty()){
            throw new NoSuchElementException("article not found");
        } else {
            return article.get();
        }
    }
    public void save(Article article){
        articleRepository.save(article);
    }
}
