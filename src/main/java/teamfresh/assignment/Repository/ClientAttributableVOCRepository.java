
package teamfresh.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamfresh.assignment.entity.ClientAttributableVOC;

@Repository
public interface ClientAttributableVOCRepository extends JpaRepository<ClientAttributableVOC, Long> {
}