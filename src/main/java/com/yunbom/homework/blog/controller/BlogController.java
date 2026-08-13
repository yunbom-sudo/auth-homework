package com.yunbom.homework.blog.controller;

import com.yunbom.homework.blog.dto.request.BlogRequest;
import com.yunbom.homework.blog.dto.response.BlogResponse;
import com.yunbom.homework.blog.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BlogResponse createBlog(@Valid @RequestBody BlogRequest request){
        return blogService.createBlog(request);
    }

    @GetMapping("/all")
    public List<BlogResponse> findAll(){
        return blogService.findAll();
    }

    @GetMapping("/{id}")
    public BlogResponse findById(@PathVariable Long id){
        return blogService.findBlogById(id);
    }

    @GetMapping
    public List<BlogResponse> findByTitle(@RequestParam String title){
        return blogService.findBlogByTitle(title);
    }

    @PutMapping("/{id}")
    public BlogResponse updateBlog(@PathVariable Long id,@Valid @RequestBody BlogRequest request){
        return blogService.updateBlog(id,request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
    }

}
