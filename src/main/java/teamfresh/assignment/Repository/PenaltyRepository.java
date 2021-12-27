package teamfresh.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamfresh.assignment.entity.Penalty;
@Repository
public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
}