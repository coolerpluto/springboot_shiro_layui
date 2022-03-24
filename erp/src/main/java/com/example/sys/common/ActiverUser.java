package com.example.sys.common;

import com.example.sys.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActiverUser {
    private User user;
    private List<String> roles;
    private List<String> permissions;
}
