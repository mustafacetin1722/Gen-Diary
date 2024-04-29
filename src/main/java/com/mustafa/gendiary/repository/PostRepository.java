package com.mustafa.gendiary.repository;

import com.mustafa.gendiary.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    Post findByUuid(String uuid);
}
