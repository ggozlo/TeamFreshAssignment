package teamfresh.assignment.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import teamfresh.assignment.AttributableType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * VOC 관리 Entity
 * 조인전략 상속 관계 매핑을 통한 귀책 관리
 */

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "attributable", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public class VoiceOfCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // voc 내용
    @Lob
    private String vocContents;

    // 등록일시
    private LocalDateTime registrationTime;


    @Enumerated(EnumType.STRING)
    private AttributableType attributableType;

    // 클레임을 인입한 고객사
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientCompany complainedClient;

    // 배상정보, 배상할 필요 없다면 null
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "targetVOC")
    private Compensation compensation;

    public VoiceOfCustomer(AttributableType attributableType,String contents, @Nullable ClientCompany complainedClient) {
        this.vocContents = contents;
        this.registrationTime = LocalDateTime.now();
        this.attributableType = attributableType;
        this.complainedClient = complainedClient;
        this.compensation = null;
    }
}
