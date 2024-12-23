package com.example.lab12.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty(message = "Title cannot be empty")
    @Column(unique = true , nullable = false)
//    @Size(min = 3 , max = 20)
    private String title;
//    @NotEmpty(message = "Body cannot be empty")
    @Column(nullable = false)
//    @Size(min = 3 , max = 200)
    private String body;

    /// Relations

    @ManyToOne
    @JsonIgnore
    private User user;

}
