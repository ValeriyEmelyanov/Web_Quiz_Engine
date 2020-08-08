package engine.controllers;

import engine.dtos.UserDto;
import engine.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/register")
    public String register(@Valid @RequestBody UserDto userDto) {
        userService.register(userDto);
        return "User was registered";
    }
}
