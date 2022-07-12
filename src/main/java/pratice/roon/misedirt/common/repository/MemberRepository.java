package pratice.roon.misedirt.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pratice.roon.misedirt.common.entity.Member;

public interface MemberRepository extends JpaRepository<Member,String> {
    Member findByUsername(String username);
}
