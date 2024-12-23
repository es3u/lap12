package com.example.lab12.Service;

import com.example.lab12.ApiResponse.ApiException;
import com.example.lab12.Model.User;
import com.example.lab12.Respository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService  {

    private final AuthRepository authRepository;

    public void registerUser(User user){
        user.setRole("ADMIN");
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }

    public List<User> getAllUsers(Integer myUser_id){
        User myUser = authRepository.findUserById(myUser_id);
        if (myUser == null) {
            throw new ApiException("wrong user id");
        }
        return authRepository.findAll();
    }

    public void deleteUser(Integer myUser_id){
        User myUser = authRepository.findUserById(myUser_id);
        if (myUser == null) {
            throw new ApiException("wrong user id");
        }
        authRepository.delete(myUser);
    }

    public void updateUser(Integer myUser_id , User user){
        User myUser = authRepository.findUserById(myUser_id);
        if (myUser == null) {
            throw new ApiException("wrong user id");
        }
        if (!myUser.getId().equals(user.getId())){
            throw new ApiException("wrong user id");
        }
        myUser.setUsername(user.getUsername());
        myUser.setPassword(user.getPassword());
        myUser.setEmail(user.getEmail());
        myUser.setFirstName(user.getFirstName());
        myUser.setLastName(user.getLastName());
        myUser.setPhoneNumber(user.getPhoneNumber());

        authRepository.save(myUser);
    }


}
