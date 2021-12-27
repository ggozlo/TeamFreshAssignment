package teamfresh.assignment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import teamfresh.assignment.AttributableType;

import javax.persistence.*;

/**
 * 운송기사에게 귀책이 있을때의 조인 테이블
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("d")
public class DriversAttributableVOC extends VoiceOfCustomer{



    // 귀책 사유
    @Lob
    private String attributableReason;

    // 귀책이 있는 운전사
    @ManyToOne(fetch = FetchType.LAZY)
    private TransportationDriver attributableDiver;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "driversAttributableVOC")
    private Penalty penalty;

    public DriversAttributableVOC(String content, ClientCompany complainedClient, String attributableReason, TransportationDriver driver) {
        super(AttributableType.TransportDriver,content, complainedClient);
        this.attributableReason = attributableReason;
        this.attributableDiver = driver;
    }
}
