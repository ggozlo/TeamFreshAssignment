package teamfresh.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamfresh.assignment.entity.TransportationDriver;

@Repository
public interface TransportationDriverRepository extends JpaRepository<TransportationDriver, Long> {
    TransportationDriver findByDriverName(String driverName);
}