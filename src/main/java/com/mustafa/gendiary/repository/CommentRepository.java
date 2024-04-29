package com.mustafa.gendiary.repository;

import com.mustafa.gendiary.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment findByUuid(String uuid);
}
