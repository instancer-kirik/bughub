package com.instance.bughub.services;

import com.instance.bughub.entities.Comment;
import com.instance.bughub.repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentService {

    private final CommentRepo commentRepository;

    @Autowired
    public CommentService(CommentRepo commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}