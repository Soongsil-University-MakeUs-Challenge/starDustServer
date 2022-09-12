package ssumc.stardust.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssumc.stardust.src.repository.MemberRepository;
import ssumc.stardust.utils.JwtService;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
}
