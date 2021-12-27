package teamfresh.assignment.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import teamfresh.assignment.AttributableType;
import teamfresh.assignment.entity.TransportationDriver;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class VOCDto {

    // voc 내용
    private String vocContents;
    // 등록일시
    private LocalDateTime registrationTime;

    // 귀책 타입
    private AttributableType attributableType;
    // 귀책 내용
    private String attributableReason;
    // 귀책이 있는 당사자, 당사
    private String attributableName;

    // 배상 내용
    private String compensationContent;
    // 배상 금액
    private int compensationAmount;

    // 패널티 내용
    private String penaltyContents;
    // 패널티 금액
    private int penaltyAmount;

    // 이의제기 여부
    private Boolean objection;
    // 패널티 확인 여부
    private boolean isChecked;

    @QueryProjection

    public VOCDto(String vocContents, LocalDateTime registrationTime, AttributableType attributableType,
                  String attributableReason, String attributableName, String compensationContent,
                  int compensationAmount, String penaltyContents, int penaltyAmount, Boolean objection, boolean isChecked) {
        this.vocContents = vocContents;
        this.registrationTime = registrationTime;
        this.attributableType = attributableType;
        this.attributableReason = attributableReason;
        this.attributableName = attributableName;
        this.compensationContent = compensationContent;
        this.compensationAmount = compensationAmount;
        this.penaltyContents = penaltyContents;
        this.penaltyAmount = penaltyAmount;
        this.objection = objection;
        this.isChecked = isChecked;
    }
}
