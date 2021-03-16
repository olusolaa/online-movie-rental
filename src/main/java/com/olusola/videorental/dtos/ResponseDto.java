package com.olusola.videorental.dtos;

import com.olusola.videorental.model.user_db.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
    String role;
    boolean isSuccessful;
    int status;
    String error;
    Object data;
    User user;
}
