package com.eTaskifyAPI.etaskify.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private Task task;
}
