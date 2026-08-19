package com.yunbom.homework.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlogResponse {

    private Long blogId;
    private Long authorId;
    private String title;
    private String content;
}
