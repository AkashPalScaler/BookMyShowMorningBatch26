package com.scaler.bookmyshowmorning.controllers;

import com.scaler.bookmyshowmorning.DTOs.ResponseStatus;
import com.scaler.bookmyshowmorning.DTOs.UserSignupRequestDTO;
import com.scaler.bookmyshowmorning.DTOs.UserSignupResponseDTO;
import com.scaler.bookmyshowmorning.models.User;
import com.scaler.bookmyshowmorning.services.BookingService;
import com.scaler.bookmyshowmorning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    public UserSignupResponseDTO signup(UserSignupRequestDTO requestDTO){
        UserSignupResponseDTO responseDTO = new UserSignupResponseDTO();
        try{
            User user = userService.signup(
                    requestDTO.getName(),
                    requestDTO.getEmail(),
                    requestDTO.getPassword()
            );

            responseDTO.setUserId(user.getId());
            responseDTO.setMessage("User sign up successful");
            responseDTO.setStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDTO.setStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage("User sign up failed - " + e.getMessage());
        }
        return responseDTO;
    }
}
