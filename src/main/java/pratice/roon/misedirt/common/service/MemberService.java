package pratice.roon.misedirt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratice.roon.misedirt.common.entity.Member;
import pratice.roon.misedirt.common.repository.MemberRepository;

@Service("common")
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public String saveMember(Member member){
        return memberRepository.save(member).getUsername();
    }

    @Transactional
    public void update(String username, String newUsername) {
        Member member = memberRepository.findByUsername(username);
        member.setUsername(newUsername);
    }

    public Member findByUsername(String username){
        return memberRepository.findByUsername(username);
    }
}
