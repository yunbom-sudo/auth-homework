package com.yunbom.homework.blog.service;

import com.yunbom.homework.blog.dto.request.BlogRequest;
import com.yunbom.homework.blog.dto.response.BlogResponse;
import com.yunbom.homework.blog.entity.BlogEntity;
import com.yunbom.homework.blog.repository.BlogRepository;
import jakarta.persistence.Id;
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

        BlogEntity entity = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id에 글이 존재하지 않습니다."));
        return new BlogResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getContent()
        );
    }

    @Transactional(readOnly = true)
    public BlogResponse findBlogByTitle(String title){

        BlogEntity entity = blogRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("해당 제목의 글이 존재하지 않습니다."));
        return new BlogResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getContent()
        );
    }


}
