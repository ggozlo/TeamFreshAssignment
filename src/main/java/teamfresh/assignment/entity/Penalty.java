package teamfresh.assignment.entity;

import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

/**
 * 운송사 기사에 부여할 패널티 관리 엔티티
 */

@Entity
@NoArgsConstructor
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 패널티 금액
    private int penaltyAmount;

    // 패널티 확인 여부
    private boolean isChecked;

    // 이의신청 여부
    // 최초는 null
    private Boolean objection;

    // 패널티 부여 사유
    @Lob
    private String penaltyContents;

    // 패널티 대상 운송기사
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private TransportationDriver targetDriver;

    // 연관 VOC, nullable
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private DriversAttributableVOC driversAttributableVOC;

    public Penalty(int penaltyAmount, TransportationDriver targetDriver,DriversAttributableVOC driversAttributableVOC, String  penaltyContents) {
        this.penaltyAmount = penaltyAmount;
        this.isChecked = false;
        this.objection = null;
        this.targetDriver = targetDriver;
        this.driversAttributableVOC = driversAttributableVOC;
        this.penaltyContents = penaltyContents;
    }
}
