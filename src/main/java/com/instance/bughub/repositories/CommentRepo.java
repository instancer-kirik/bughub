package com.instance.bughub.repositories;

import com.instance.bughub.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    // Basic CRUD methods are automatically provided
}