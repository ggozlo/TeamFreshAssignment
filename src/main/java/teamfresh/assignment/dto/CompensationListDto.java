package teamfresh.assignment.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CompensationListDto {

    // 배상 id
    private Long id;

    // voc 내용
    private String vocContents;

    // 클레임을 인입한 고객사명
    private String complainedClientName;

    // 배상 내용
    private String compensationContent;

    // 배상 금액
    private int compensationAmount;

    // 배상 완료 여부
    private boolean isCompleted;

    @QueryProjection
    public CompensationListDto(Long id, String vocContents, String complainedClientName, String compensationContent, int compensationAmount, boolean isCompleted) {
        this.id = id;
        this.vocContents = vocContents;
        this.complainedClientName = complainedClientName;
        this.compensationContent = compensationContent;
        this.compensationAmount = compensationAmount;
        this.isCompleted = isCompleted;
    }
}
