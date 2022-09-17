package ssumc.stardust.src.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssumc.stardust.src.service.UserService;
import ssumc.stardust.utils.JwtService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService UserService;
    private final JwtService jwtService;
}
