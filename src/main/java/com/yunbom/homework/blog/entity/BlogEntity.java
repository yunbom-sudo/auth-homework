package com.yunbom.homework.blog.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}