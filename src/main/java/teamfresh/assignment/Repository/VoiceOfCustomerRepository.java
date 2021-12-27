package teamfresh.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamfresh.assignment.entity.VoiceOfCustomer;

@Repository
public interface VoiceOfCustomerRepository extends JpaRepository<VoiceOfCustomer, Long> {
}