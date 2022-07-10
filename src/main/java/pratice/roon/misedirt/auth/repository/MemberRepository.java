package pratice.roon.misedirt.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pratice.roon.misedirt.auth.entity.Member;

public interface MemberRepository extends JpaRepository<Member,String> {
    Member findByUsername(String username);
}
