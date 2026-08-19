package com.yunbom.homework.blog.service;

import com.yunbom.homework.blog.dto.request.BlogRequest;
import com.yunbom.homework.blog.dto.response.BlogResponse;
import com.yunbom.homework.blog.entity.BlogEntity;
import com.yunbom.homework.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService{

    private final BlogRepository blogRepository;

    private Long getCurrentUserId(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return (Long) authentication.getPrincipal();
    }

    private BlogResponse toResponse(BlogEntity blog){
        return new BlogResponse(
                blog.getBlogId(),
                blog.getAuthorId(),
                blog.getTitle(),
                blog.getContent()
        );
    }

    @Transactional
    public BlogResponse createBlog(BlogRequest request){

        BlogEntity blog = BlogEntity.builder()
                .authorId(getCurrentUserId())
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        blogRepository.save(blog);

        return toResponse(blog);
    }

    @Transactional(readOnly = true)
    public List<BlogResponse> findAll(){

        List<BlogEntity> blogs = blogRepository.findAll();

        return blogs.stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public BlogResponse findBlogById(Long id){

        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id에 게시글이 존재하지 않습니다."));

        return toResponse(blog);
    }

    @Transactional(readOnly = true)
    public List<BlogResponse> findBlogByTitle(String title){

        List<BlogEntity> blogs = blogRepository.findByTitle(title);

        if(blogs.isEmpty()){
            throw new RuntimeException("해당 제목의 게시글이 존재하지 않습니다.");
        }

        return blogs.stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public BlogResponse updateBlog(Long id,BlogRequest request){

        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글이 존재하지 않습니다."));

        if (!blog.getAuthorId().equals(getCurrentUserId())) {
            throw new RuntimeException("해당 게시글의 게시자만 수정할 수 있습니다.");
        }

        blog.update(
                request.getTitle(),
                request.getContent()
        );

        return toResponse(blog);
    }

    @Transactional
    public void deleteBlog(Long id){

        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글은 존재하지 않습니다."));

        if (!blog.getAuthorId().equals(getCurrentUserId())) {
            throw new RuntimeException("해당 게시글의 게시자만 삭제할 수 있습니다.");
        }

        blogRepository.delete(blog);
    }


}
