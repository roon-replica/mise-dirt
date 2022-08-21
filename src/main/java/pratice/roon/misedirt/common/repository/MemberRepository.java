package pratice.roon.misedirt.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pratice.roon.misedirt.common.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {
    Optional<Member> findByUsername(String username);
}
