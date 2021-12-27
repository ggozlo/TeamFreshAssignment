package teamfresh.assignment.entity;

import lombok.NoArgsConstructor;
import teamfresh.assignment.AttributableType;

import javax.persistence.*;

/**
 * 고객사 귀책 VOC
 */
@Entity
@DiscriminatorValue("c")
@NoArgsConstructor
public class ClientAttributableVOC extends VoiceOfCustomer{

    // 고객사의 귀책 사유
    @Lob
    private String attributableReason;


    public ClientAttributableVOC(String content, ClientCompany complainedClient,String attributableReason) {
        super(AttributableType.ClientCompany,content, complainedClient);
        this.attributableReason = attributableReason;
    }
}
