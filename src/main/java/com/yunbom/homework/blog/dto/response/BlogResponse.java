package com.yunbom.homework.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlogResponse {

    private Long id;
    private String title;
    private String content;
}
