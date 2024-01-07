package com.instance.bughub.DTO;

import lombok.Data;

@Data
public class CommentDTO {
    private String text;
    private Integer userId;
    private Integer bugId;

    // Getters and setters
}