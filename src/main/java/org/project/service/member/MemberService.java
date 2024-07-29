package org.project.service.member;

import org.project.dto.MemberLoginDto;
import org.project.dto.MemberRegisterDto;

public interface MemberService {

    void register(MemberRegisterDto registerDto);

    String login(MemberLoginDto loginDto);

}
