package com.instance.bughub.DTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentDisplayDTO {
    private Long id;
    private String text;
    private String username; // Assuming you want to display the username
    private LocalDateTime createdAt; // If you want to display the time of the comment

    // Getters and setters
}