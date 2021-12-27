package teamfresh.assignment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 고객사 관리 엔티티
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClientCompany{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 고객사명
    @Column(nullable = false)
    private String companyName;

    // 담당자 이름
    private String managerName;

    // 고객사 전화번호
    @Column(nullable = false)
    private String tel;

    // 해당 관계사의 클레임으로 등록된 VOC 리스트
    @OneToMany(mappedBy = "complainedClient")
    private List<VoiceOfCustomer> complainedVOCList = new ArrayList<>();

    public ClientCompany(String companyName, String managerName, String tel) {
        this.companyName = companyName;
        this.managerName = managerName;
        this.tel = tel;
    }
}

