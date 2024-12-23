package com.example.lab12.Controller;

import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Respository.BlogRepository;
import com.example.lab12.Service.AuthService;
import com.example.lab12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/myBlog")
    public ResponseEntity getMyBlog(@AuthenticationPrincipal User user ){
       return ResponseEntity.ok(blogService.getMyBlogs(user.getId()));
    }

    @PostMapping("/addBlog")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user ,@RequestBody@Valid Blog blog){
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.ok().body("blog added");
    }

    @PutMapping("/updateBlog/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user , @PathVariable Integer blogId ,@RequestBody@Valid Blog blog){
        blogService.updateBlog(user.getId(),blogId,blog);
        return ResponseEntity.ok().body("blog updated");
    }

    @DeleteMapping("/deleteBlog/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user , @PathVariable Integer blogId){
        blogService.deleteBlog(user.getId(),blogId);
        return ResponseEntity.ok().body("blog deleted");
    }
    @GetMapping("/getBlogById/{blogId}")
    public Blog getBlogById(@AuthenticationPrincipal User user ,@PathVariable Integer blogId){
        return blogService.getBlogById(user.getId(),blogId);

    }
    @GetMapping("/getBlogByTitle/{title}")
    public Blog getBlogByTitle(@AuthenticationPrincipal User user ,@PathVariable String title){
        return blogService.getBlogByTitle(user.getId(), title);
    }

}
