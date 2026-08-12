package com.yunbom.homework.blog.controller;

import com.yunbom.homework.blog.dto.request.BlogRequest;
import com.yunbom.homework.blog.dto.response.BlogResponse;
import com.yunbom.homework.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BlogResponse createBlog(@RequestBody BlogRequest request){
        return blogService.createBlog(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public BlogResponse findById(@PathVariable Long id){
        return blogService.findBlogById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{title}")
    public BlogResponse findByTitle(@PathVariable String title){
        return blogService.findBlogByTitle(title);
    }
}
