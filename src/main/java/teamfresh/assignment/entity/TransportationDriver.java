package teamfresh.assignment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 운송사 기사 관리 엔티티
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TransportationDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 운송기사 이름
    private String driverName;

    // 운송사 명
    private String companyName;

    // 운송시가 전화번호
    private String tel;

    // 운송사 번호
    private String companyTel;

    // 운전사귀책인 VOC 리스트
    @OneToMany(mappedBy = "attributableDiver")
    private List<DriversAttributableVOC> attributableList = new ArrayList<>();

    @OneToMany(mappedBy = "targetDriver")
    private List<Penalty> penaltyList = new ArrayList<>();


    public TransportationDriver(String driverName, String companyName, String tel, String companyTel) {
        this.driverName = driverName;
        this.companyName = companyName;
        this.tel = tel;
        this.companyTel = companyTel;
    }
}
