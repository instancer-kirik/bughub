package com.instance.bughub.controllers;

import com.instance.bughub.DTO.CommentDTO;
import com.instance.bughub.entities.Bug;
import com.instance.bughub.entities.Comment;
import com.instance.bughub.entities.User;
import com.instance.bughub.repositories.BugRepo;
import com.instance.bughub.repositories.UserRepo;
import com.instance.bughub.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final UserRepo userRepository;
    private final BugRepo bugRepository;

    @Autowired
    public CommentController(CommentService commentService, UserRepo userRepository, BugRepo bugRepository) {
        this.commentService = commentService;
        this.userRepository = userRepository;
        this.bugRepository = bugRepository;
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Bug bug = bugRepository.findById(commentDTO.getBugId())
                .orElseThrow(() -> new RuntimeException("Bug not found"));

        Comment newComment = new Comment();
        newComment.setText(commentDTO.getText());
        newComment.setUser(user);
        newComment.setBug(bug);

        Comment savedComment = commentService.saveComment(newComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }


    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }
}