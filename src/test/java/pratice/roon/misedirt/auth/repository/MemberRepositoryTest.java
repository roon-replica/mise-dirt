package pratice.roon.misedirt.auth.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import pratice.roon.misedirt.auth.entity.Member;
import pratice.roon.misedirt.auth.entity.MemberRole;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    static {
        System.setProperty("spring.config.location", "classpath:/application.yml,classpath:/openapi-secret.yml");
    }

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 100).forEach(idx -> {
            Member member = Member.builder()
                    .username("user" + idx)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            if (idx < 50) {
                member.setMemberRole(MemberRole.GUEST);
            } else if (idx < 95) {
                member.setMemberRole(MemberRole.USER);
            } else {
                member.setMemberRole(MemberRole.ADMIN);
            }

            memberRepository.save(member);
        });
    }
}