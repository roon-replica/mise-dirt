package pratice.roon.misedirt.openApi.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pratice.roon.misedirt.openApi.entity.Dirt;

@Repository
public interface DirtRepository extends JpaRepository<Dirt, Integer> {
}
