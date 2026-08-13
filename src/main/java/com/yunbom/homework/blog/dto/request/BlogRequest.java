package com.yunbom.homework.blog.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogRequest {

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(max = 100, message = "제목은 100글자를 넘을 수 없습니다.")
    private String title;

    @NotBlank(message = "본문 내용은 비어있을 수 없습니다.")
    private String content;
}
