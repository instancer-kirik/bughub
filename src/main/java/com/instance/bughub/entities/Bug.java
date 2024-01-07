package com.instance.bughub.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="bugs")
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer bugID;


    private String slug;
    private String description;
    private String solution;
    private String status;
    private Blob screenshot;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int encounters;
    private String comment;

    @ManyToMany
    @JoinTable(
            name = "users_afflicted",
            joinColumns = @JoinColumn(name = "bug_id", referencedColumnName = "bugID"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<User> afflictedUsers = new HashSet<>();
    public void afflictUser(User user){

        afflictedUsers.add(user);
    }


    @OneToMany(mappedBy = "bug")
    private List<Comment> comments;




    public Set<User> getAfflictedUsers() {
        return afflictedUsers;
    }

    public void setAfflictedUsers(Set<User> afflictedUsers) {
        this.afflictedUsers = afflictedUsers;
    }


}
