package teamfresh.assignment.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import teamfresh.assignment.dto.CompensationListDto;
import teamfresh.assignment.dto.QCompensationListDto;
import teamfresh.assignment.dto.QVOCDto;
import teamfresh.assignment.dto.VOCDto;
import teamfresh.assignment.entity.*;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CustomRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    private QVoiceOfCustomer qVOC = QVoiceOfCustomer.voiceOfCustomer;
    private QTransportationDriver qDriver = QTransportationDriver.transportationDriver;
    private QClientCompany qCompany = QClientCompany.clientCompany;
    private QPenalty qPenalty = QPenalty.penalty;
    private QDriversAttributableVOC qDriverVOC = QDriversAttributableVOC.driversAttributableVOC;
    private QClientAttributableVOC qCompanyVOC= QClientAttributableVOC.clientAttributableVOC;
    private QCompensation qCompensation = QCompensation.compensation;

    public VoiceOfCustomer findVOCById(Long vocId) {
        return queryFactory
                .selectFrom(qVOC)
                .where(qVOC.id.eq(vocId))
                .fetchOne();
    }

    public void penaltyChecked(Long penaltyId) {
        queryFactory
                .update(qPenalty)
                .set(qPenalty.isChecked, true)
                .where(qPenalty.id.eq(penaltyId))
                .execute();
    }

    public DriversAttributableVOC findDriversAttributableVOCById(Long vocId) {
        return queryFactory
                .selectFrom(qDriverVOC)
                .where(qDriverVOC.id.eq(vocId))
                .fetchOne();
    }

    public void savePenalty(Penalty penalty) {
        entityManager.persist(penalty);
    }

    public void saveCompensation(Compensation compensation) {
        entityManager.persist(compensation);
    }

    public void saveDriversAttributableVOC(DriversAttributableVOC driversAttributableVOC) {
        entityManager.persist(driversAttributableVOC);
    }

    public ClientCompany findClientCompanyByCompanyName(String complainedClient) {
        return queryFactory
                .selectFrom(qCompany)
                .where(companyNameEqual(complainedClient))
                .fetchOne();
    }

    public TransportationDriver findTransportDriverByDriverName(String attributableName) {
        return queryFactory
                .selectFrom(qDriver)
                .where(qDriver.driverName.eq(attributableName))
                .fetchOne();
    }

    public List<CompensationListDto> compensationList(String companyName, Integer maxAmount, Integer minAmount) {
        return queryFactory
                .select(new QCompensationListDto(
                        qCompensation.id,
                        qVOC.vocContents,
                        qCompany.companyName,
                        qCompensation.compensationContent,
                        qCompensation.compensationAmount,
                        qCompensation.isCompleted))
                .from(qCompensation)
                .join(qCompensation.targetVOC, qVOC)
                .join(qVOC.complainedClient, qCompany)
                .where(companyNameEqual(companyName), minAmount(minAmount), maxAmount(maxAmount))
                .fetch();
    }

    private BooleanExpression maxAmount(Integer maxAmount) {
        if (maxAmount == null) {
            return null;
        }
        return qCompensation.compensationAmount.loe(maxAmount);
    }

    private BooleanExpression minAmount(Integer minAmount) {
        if (minAmount == null) {
            return null;
        }
        return qCompensation.compensationAmount.goe(minAmount);
    }

    private BooleanExpression companyNameEqual(String companyName) {
        if (companyName == null || companyName.isEmpty()) {
            return null;
        }
        return qCompany.companyName.eq(companyName);
    }

    public void saveClientAttributableVoc(ClientAttributableVOC voc) {
        entityManager.persist(voc);
    }


    public List<VOCDto> findVocListWhenDriversAttributable() {
        return queryFactory
                .select(new QVOCDto(
                        qDriverVOC.vocContents,
                        qDriverVOC.registrationTime,
                        qDriverVOC.attributableType,
                        qDriverVOC.attributableReason,
                        qDriver.driverName,
                        qCompensation.compensationContent,
                        qCompensation.compensationAmount,
                        qPenalty.penaltyContents,
                        qPenalty.penaltyAmount,
                        qPenalty.objection,
                        qPenalty.isChecked))
                .from(qDriverVOC)
                .join(qDriverVOC.compensation, qCompensation)
                .join(qDriverVOC.complainedClient, qCompany)
                .join(qDriverVOC.attributableDiver, qDriver)
                .join(qDriverVOC.penalty, qPenalty)
                .fetch();
    }

    public List<VOCDto> findVocListWhenClientsAttributable() {
        // 미구현
        return null;
    }

    public List<VOCDto> findVocListDefault() {
        // 미구현
        return null;
    }
}
