package com.example.tutor.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRsponDTO {
    private Long id;
    private String name;
    private String major;

    private String courseName;
}
