package teamfresh.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamfresh.assignment.entity.DriversAttributableVOC;

@Repository
public interface DriversAttributableVOCRepository extends JpaRepository<DriversAttributableVOC, Long> {
}