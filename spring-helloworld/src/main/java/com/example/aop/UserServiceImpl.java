package com.example.aop;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    public void addUser() {
        System.out.println("添加用户成功");
    }

    public String deleteUser() {
        System.out.println("删除用户成功");
        return "aab";
    }
}
