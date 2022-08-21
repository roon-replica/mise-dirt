package pratice.roon.misedirt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratice.roon.misedirt.common.entity.Member;
import pratice.roon.misedirt.common.entity.MemberRole;
import pratice.roon.misedirt.common.exception.MemberNotFoundException;
import pratice.roon.misedirt.common.repository.MemberRepository;

import java.util.List;

@Service("common")
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Long createMember(String username, String password, MemberRole memberRole) {
        return memberRepository.save(
                Member.builder()
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .memberRole(memberRole)
                        .build()
        ).getId();
    }

    @Transactional
    public void update(String username, String newUsername) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberNotFoundException("member with " + username + " not exist"));

        member.update(newUsername);
    }

    public Member getUserByName(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberNotFoundException("member with " + username + " not exist"));
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}
