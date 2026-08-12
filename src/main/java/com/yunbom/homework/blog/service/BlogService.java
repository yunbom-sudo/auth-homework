package com.yunbom.homework.blog.service;

import com.yunbom.homework.blog.dto.request.BlogRequest;
import com.yunbom.homework.blog.dto.response.BlogResponse;
import com.yunbom.homework.blog.entity.BlogEntity;
import com.yunbom.homework.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService{

    public final BlogRepository blogRepository;

    @Transactional
    public BlogResponse createBlog(BlogRequest request){

        BlogEntity blog = BlogEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        blogRepository.save(blog);

        return new BlogResponse(
                blog.getId(),
                blog.getTitle(),
                blog.getContent()
        );
    }

    @Transactional(readOnly = true)
    public BlogResponse findBlogById(Long id){

        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id에 게시글이 존재하지 않습니다."));
        return new BlogResponse(
                blog.getId(),
                blog.getTitle(),
                blog.getContent()
        );
    }

    @Transactional(readOnly = true)
    public BlogResponse findBlogByTitle(String title){

        BlogEntity blog = blogRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("해당 제목의 게시글이 존재하지 않습니다."));
        return new BlogResponse(
                blog.getId(),
                blog.getTitle(),
                blog.getContent()
        );
    }

    @Transactional
    public BlogResponse updateBlog(Long id,BlogRequest request){

        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글이 존재하지 않습니다."));

        blog.update(
                request.getTitle(),
                request.getContent()
        );

        return new BlogResponse(
                blog.getId(),
                blog.getTitle(),
                blog.getContent()
        );
    }

    @Transactional
    public BlogResponse updateByTitle(String title, BlogRequest request){

        BlogEntity blog = blogRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("해당 제목의 게시글은 존재하지 않습니다."));

        blog.update(
                request.getTitle(),
                request.getContent()
        );

        return new BlogResponse(
                blog.getId(),
                blog.getTitle(),
                blog.getContent()
        );
    }

    @Transactional
    public void deleteBlog(Long id){

        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 개시글은 존재하지 않습니다."));

        blogRepository.delete(blog);
    }
    @Transactional
    public void deleteByTitle(String title){

        BlogEntity blog = blogRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("해당 제목의 게시글은 존재하지 않습니다."));

        blogRepository.delete(blog);
    }

}
