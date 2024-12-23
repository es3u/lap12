package com.example.lab12.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty(message = "Username cannot be empty")
    @Column(unique = true , nullable = false)
//    @Size(min = 3 , max = 20)
    private String username;

//    @NotEmpty(message = "Password cannot be empty")
//    @Size(min = 8 , max = 20)
    @Column(nullable = false)
//    @Pattern(
//            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
//            message = "Password must be at least 8 characters long, contain one uppercase letter, one lowercase letter, one number, and one special character."
//    )
    private String password;

//    @NotEmpty(message = "Role cannot be empty")
    @Column(nullable = false)
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role must be either ADMIN or USER")
    private String role  ;

//    @NotEmpty(message = "Email cannot be empty")
    @Column(unique = true , nullable = false)
//    @Size(min = 5 , max = 50)
//    @Email(message = "Email is not valid")
    private String email;

//    @NotEmpty(message = "First name cannot be empty")
//    @Size(min = 2 , max = 20)
    @Column(nullable = false)
    private String firstName;

//    @NotEmpty(message = "Last name cannot be empty")
//    @Size(min = 2 , max = 20)
    @Column(nullable = false)
    private String lastName;

//    @NotEmpty(message = "Phone number cannot be empty")
    @Column(nullable = false)
//    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    /// Relations

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs;







}
