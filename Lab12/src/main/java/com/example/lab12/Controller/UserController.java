package com.example.lab12.Controller;

import com.example.lab12.Model.User;
import com.example.lab12.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody@Valid User user){
        authService.registerUser(user);
        return ResponseEntity.ok().body("user registered");
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers(@AuthenticationPrincipal User User){
        return ResponseEntity.ok().body(authService.getAllUsers(User.getId()));
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User User){
        authService.deleteUser(User.getId());
        return ResponseEntity.ok().body("deleted successfully");
    }
    @PutMapping("/updateUser")
    public ResponseEntity updateUser(@AuthenticationPrincipal User User , @RequestBody@Valid User user){
        authService.updateUser(User.getId(),user);
        return ResponseEntity.ok().body("updated successfully");
    }


}
