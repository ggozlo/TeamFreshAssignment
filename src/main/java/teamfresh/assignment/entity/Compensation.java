package teamfresh.assignment.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 배상정보 관리 엔티티
 */

@Entity
@NoArgsConstructor
public class Compensation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 배상해야 할 VOC
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private VoiceOfCustomer targetVOC;

    // 배상 받을 기업정보 컬럼 보상 테이블의 독립성을 보장하기 위한 컬럼이라 단방향으로 설정함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ClientCompany targetCompany;

    // 배상 내용
    @Lob
    private String compensationContent;

    // 배상 금액
    private int compensationAmount;

    // 배상 완료 여부
    private boolean isCompleted;

    public Compensation(VoiceOfCustomer targetVOC, ClientCompany targetCompany, String compensationContent, int compensationAmount) {
        this.targetVOC = targetVOC;
        this.targetCompany = targetCompany;
        this.compensationContent = compensationContent;
        this.compensationAmount = compensationAmount;
        this.isCompleted = false;
    }
}
