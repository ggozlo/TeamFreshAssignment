package teamfresh.assignment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import teamfresh.assignment.Repository.ClientAttributableVOCRepository;
import teamfresh.assignment.Repository.ClientCompanyRepository;
import teamfresh.assignment.Repository.DriversAttributableVOCRepository;
import teamfresh.assignment.Repository.TransportationDriverRepository;
import teamfresh.assignment.entity.*;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
public class DBTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    JPAQueryFactory queryFactory;
    @Autowired
    ClientCompanyRepository companyRepository;
    @Autowired
    TransportationDriverRepository driverRepository;
    @Autowired
    DriversAttributableVOCRepository driversAttributableVOCRepository;
    @Autowired
    ClientAttributableVOCRepository clientAttributableVOCRepository;
    String clientName = "한라산 식품";
    String driverName = "홍길동";

    QVoiceOfCustomer qVOC = QVoiceOfCustomer.voiceOfCustomer;
    QTransportationDriver qDriver = QTransportationDriver.transportationDriver;
    QClientCompany qCompany = QClientCompany.clientCompany;
    QPenalty qPenalty = QPenalty.penalty;
    QDriversAttributableVOC qDriverVOC = QDriversAttributableVOC.driversAttributableVOC;
    QClientAttributableVOC qCompanyVOC= QClientAttributableVOC.clientAttributableVOC;
    QCompensation qCompensation = QCompensation.compensation;


    @BeforeEach
    void setUp() {
        TransportationDriver driver = new TransportationDriver(driverName, "생생운송", "010-2345-1235", "02-123-4573");
        driverRepository.save(driver);
        ClientCompany company = new ClientCompany(clientName, "백두산", "010-4567-9865");
       companyRepository.save(company);
        DriversAttributableVOC driverVoc = new DriversAttributableVOC("운송기사 귀책", company, "지각", driver);
        driversAttributableVOCRepository.save(driverVoc);
        ClientAttributableVOC clientVoc = new ClientAttributableVOC("고객사 귀책", company,"누락 납품");
        clientAttributableVOCRepository.save(clientVoc);
    }

    @Test
    public void driverInsert() {
        TransportationDriver driver = new TransportationDriver("김운전", "생생운송", "010-2345-1235", "02-123-4573");
        TransportationDriver save = driverRepository.save(driver);

        TransportationDriver findDriver = driverRepository.findById(save.getId()).get();
        Assertions.assertEquals(save.getId(), findDriver.getId());
        Assertions.assertEquals(save.getDriverName(), findDriver.getDriverName());
        Assertions.assertEquals(save.getCompanyName(), findDriver.getCompanyName());
        Assertions.assertEquals(save.getTel(), findDriver.getTel());
    }

    @Test
    public void clientInsert() {
        ClientCompany company = new ClientCompany("테스트 고객사", "홍길동", "010-4567-9865");
        ClientCompany save = companyRepository.save(company);

        ClientCompany findCompany = companyRepository.findById(save.getId()).get();
        Assertions.assertEquals(company.getId(), findCompany.getId());
        Assertions.assertEquals(company.getCompanyName(), findCompany.getCompanyName());
        Assertions.assertEquals(company.getTel(), findCompany.getTel());
        Assertions.assertEquals(company.getManagerName() , findCompany.getManagerName());

    }



    @Test
    public void driverAttributableVocRegistration() {

        ClientCompany complainedCompany = companyRepository.findByCompanyName(clientName);
        TransportationDriver driver = driverRepository.findByDriverName(driverName);
        DriversAttributableVOC driversAttributableVOC = new DriversAttributableVOC("배송상품 지연도착에 대한 클레임", complainedCompany, "운송기사 홍길동이 지연출발함", driver);
        DriversAttributableVOC save = driversAttributableVOCRepository.save(driversAttributableVOC);

        DriversAttributableVOC voc = driversAttributableVOCRepository.findById(save.getId()).get();

        System.out.println("voc.getVocContents() = " + voc.getVocContents());
    }

    @Test
    public void vocListSelect() {
    }
}
