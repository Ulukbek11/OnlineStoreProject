package com.example.OnlineStoreProject.dto;

import com.example.OnlineStoreProject.models.User;
import com.example.OnlineStoreProject.models.enums.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String fullname;

    private String email;

    private Role role;

    public UserDto(Long id, User user) {
        this.id = id;
        this.fullname = user.getFullname();
        this.email = user.getUsername();
        this.role = user.getRole();
    }

}
