package com.eTaskifyAPI.etaskify.dto;

import com.eTaskifyAPI.etaskify.enumclas.Status;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskDto {
    private String header;
    private String description;
    private Date expairedDate;
    private String situation;
    private List<Long> assignUserIds;
    private Status oldStatus;
    private Status newStatus;
}
