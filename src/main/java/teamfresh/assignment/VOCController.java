package teamfresh.assignment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import teamfresh.assignment.dto.CompensationListDto;
import teamfresh.assignment.dto.VOCDto;
import teamfresh.assignment.dto.VocRegistrationDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VOCController {

    private final VOCService vocService;

    /**
     * VOC 를 등록하는 api
     *
//     * @param attributableType   (필수)
//     *                           귀책당사자의 유형을 선택, TransportDriver - 운송기사, ClientCompany - 고객사
//     * @param vocContents        VOC 내용
//     * @param complainedClient   (필수)
//     *                           클레임을 넣은 회사의 사명을 기입
//     * @param attributableReason 귀책 사유
//     * @param attributableName   귀책 당사자/당사의 이름/사명을 기입
     */
    @PostMapping("/registration/voc")
    public void vocRegistration(VocRegistrationDto vocRegistrationDto) {
        vocService.saveVoc(vocRegistrationDto);
    }

    /**
     * 페널티 등록 API
     *
     * @param vocId           페널티가 연관될 VOC 의 등록 ID, Nullable
     * @param penaltyAmount   패널티 금액
     * @param penaltyContents 패널티 사유
     */
    @PostMapping("/registration/penalty")
    public void penaltyRegistration(@RequestParam(required = false) Long vocId, int penaltyAmount, String penaltyContents) {
        vocService.savePenalty(vocId, penaltyAmount, penaltyContents);
    }

    /**
     * 배상 등록 API
     * @param vocId                배상할 VOC 의 ID
     * @param compensationContents 배상 설명
     * @param amount               배상 금액
     */
    @PostMapping("/registration/compensation")
    public void compensationRegistration(Long vocId, String compensationContents, int amount) {
        vocService.saveCompensation(vocId, compensationContents, amount);
    }

    @PutMapping("/penalty/check")
    public void penaltyCheck(Long penaltyId) {
        vocService.checkPenalty(penaltyId);
    }


    /**
     * 배상 정보를 조회하는 Api 입니다
     * 동적 쿼리를 구현했습니다.
     *
     * @param companyName 선택 검색조건 VOC 를 등록한 회사명
     * @param minAmount   선택 검색조건 최소 배상금액
     * @param maxAmount   선택 검색조건 최대 배상금액
     * @return id : 배상 id
     * vocContents : voc 내용
     * complainedClientName : 클레임을 인입한 고객사명
     * compensationContent : 배상 내용
     * compensationAmount : 배상 금액
     * isCompleted : 배상 완료 여부
     */
    @GetMapping("/compensation/list")
    public List<CompensationListDto> compensationList(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) Integer minAmount,
            @RequestParam(required = false) Integer maxAmount) {

        return vocService.compensationList(companyName, maxAmount, minAmount);
    }

    /**
     * voc 를 조회하는 api 입니다.
     * attributableType 에 TransportDriver 변수를 지정할때만 값이 전달됩니다.
     * 고객사의 과실 또는 전체 조회는 구현하지 않았습니다.
     *
     * @param attributableType 귀책 유형별 조회를 위한 파라미터 입니다.
     * @return voc 내용 vocContents
     * 등록일시                 registrationTime
     * 귀책 타입                attributableType
     * 귀책 내용                 attributableReason
     * 귀책이 있는 당사자, 당사    attributableName
     * 배상 내용                compensationContent
     * 배상 금액                compensationAmount
     * 패널티 내용                penaltyContents
     * 패널티 금액               penaltyAmount
     * 이의제기 여부             objection
     * 패널티 확인 여부           isChecked
     */
    @GetMapping("/voc/list")
    public List<VOCDto> vocDtoList(String attributableType) {

        return vocService.vocList(attributableType);
    }

}
