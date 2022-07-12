package pratice.roon.misedirt.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratice.roon.misedirt.auth.dto.AuthDTO;
import pratice.roon.misedirt.auth.entity.Member;
import pratice.roon.misedirt.auth.repository.MemberRepository;

import java.util.Arrays;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);

        if (member == null) {
            throw new UsernameNotFoundException("username with " + username + " not exist");
        } else {
            return new AuthDTO(
                    member.getUsername(),
                    member.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_" + member.getMemberRole().name()))
            );
        }
    }

    @Transactional
    public void enrollUser(String username, String password) {
        if (memberRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("member with username " + username + " already exist");
        }

        Member member = Member.builder()
                .username(username)
                .password(password)
                .build();



        memberRepository.save(member);
    }
}
