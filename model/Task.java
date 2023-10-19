package com.eTaskifyAPI.etaskify.model;

import com.eTaskifyAPI.etaskify.enumclas.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    private String description;
    private Date expairedDate;
    private String situation;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToMany
    @JoinTable(
            name = "manage",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "appuser_id")
    )
    private List<AppUser> addedAppUsers;
}