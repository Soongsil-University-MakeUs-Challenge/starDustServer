package ssumc.stardust.src.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssumc.stardust.src.service.MemberService;
import ssumc.stardust.utils.JwtService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final JwtService jwtService;
}
