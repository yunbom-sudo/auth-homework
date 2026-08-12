package com.yunbom.homework.blog.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BlogEntity {
    private Long id;
    private String title;
    private String content;
}
