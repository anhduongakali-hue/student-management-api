package com.example.tutor.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Roles")
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 20)
    private String name;
}
