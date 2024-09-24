package com.std.TEST2.Article;


import com.std.TEST2.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public List<Article> getList(){
        return this.articleRepository.findAll();

    }
    public Article getArticleById(Integer id){
        Optional<Article> article = this.articleRepository.findById(id);
        if(article.isPresent()){
            return article.get();
        } else{
            throw new NoSuchElementException("article not found");
        }
    }
    public void save(Article article ){
        articleRepository.save(article);

    }

    public void createArticle (String title, String content, Principal principal){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        article.setAuthor(this.userRepository.findByusername(principal.getName()).get());
        this.articleRepository.save(article);
    }
    public void modify(Article article, String title, String content){
        article.setTitle(title);
        article.setContent(content);
        article.setModifyDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }
    public void delete (Article article){
        this.articleRepository.delete(article);
    }
}
