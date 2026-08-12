package com.yunbom.homework.blog.repository;

import com.yunbom.homework.blog.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<BlogEntity,Long> {

    Optional<BlogEntity> findByTitle(String title);
}
