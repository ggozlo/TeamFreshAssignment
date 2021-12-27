package teamfresh.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamfresh.assignment.entity.ClientCompany;

@Repository
public interface ClientCompanyRepository extends JpaRepository<ClientCompany, Long> {

    ClientCompany findByCompanyName(String companyName);
}