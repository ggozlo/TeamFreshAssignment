package teamfresh.assignment;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import teamfresh.assignment.Repository.ClientCompanyRepository;
import teamfresh.assignment.Repository.CustomRepository;
import teamfresh.assignment.Repository.DriversAttributableVOCRepository;
import teamfresh.assignment.Repository.TransportationDriverRepository;
import teamfresh.assignment.entity.*;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final TransportationDriverRepository driverRepository;
    private final ClientCompanyRepository companyRepository;
    private final DriversAttributableVOCRepository driversAttributableVOCRepository;
    private final CustomRepository customRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        TransportationDriver driver = new TransportationDriver("홍길동", "고속운송", "010-1234-5678", "02-123-4567");
        driverRepository.save(driver);

        ClientCompany company = new ClientCompany("백두산", "금강산", "031-4567-8945");
        companyRepository.save(company);

//        DriversAttributableVOC voc = new DriversAttributableVOC("배송지연 클레임", company, "운송기사의 늦잠", driver);
//        driversAttributableVOCRepository.save(voc);
//
//        Compensation compensation = new Compensation(voc, company, "지연배송으로 인한 배상", 50000);
//        customRepository.saveCompensation(compensation);
//
//        Penalty penalty = new Penalty(50000, driver, voc, "지연배송으로 인한 패널티 부여");
//        customRepository.savePenalty(penalty);
    }

}
