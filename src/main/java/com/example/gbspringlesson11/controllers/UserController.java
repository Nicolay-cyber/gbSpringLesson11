package com.example.gbspringlesson11.controllers;

import com.example.gbspringlesson11.converter.UserConverter;
import com.example.gbspringlesson11.dto.UserDto;
import com.example.gbspringlesson11.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Page<UserDto> getAllUsers(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "name_part", required = false) String namePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return userService.find(namePart, page).map(
                u -> UserConverter.dtoFromUser(u)
        );
    }

}
