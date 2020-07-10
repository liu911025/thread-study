package com.example.demotest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Api(tags = "用户管理")
@RestController
public class UserController {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserDto {

        private String userName;
        private LocalDate birthday;

    }

    @ApiOperation(value = "新增用户")
    @PostMapping("/user")
    public UserDto user(@RequestBody UserDto userDto) throws Exception {
        return userDto;
    }
}
