package com.scaler.bookmyshowmorning.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupResponseDTO {
    private Long userId;
    private ResponseStatus status;
    private String message;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
