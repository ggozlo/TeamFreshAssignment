package teamfresh.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamfresh.assignment.entity.Compensation;
@Repository
public interface CompensationRepository extends JpaRepository<Compensation, Long> {
}