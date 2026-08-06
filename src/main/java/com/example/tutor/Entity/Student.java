package com.example.tutor.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//DATABASE
@Entity
@Table(name = "student")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "major")
    private String major;

    //Soft delete
    private Boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY) //
    @JoinColumn(name = "course_id") //foreign key column name in the student table
    private Course course;

}
