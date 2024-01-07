package com.instance.bughub.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class
Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bug_id", nullable = false)
    private Bug bug;
    // Standard getters and setters
}