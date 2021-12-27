package teamfresh.assignment;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import teamfresh.assignment.Repository.*;
import teamfresh.assignment.dto.CompensationListDto;
import teamfresh.assignment.dto.VOCDto;
import teamfresh.assignment.dto.VocRegistrationDto;
import teamfresh.assignment.entity.*;
import teamfresh.assignment.vocregistrationhandler.VOCSaveHandler;

import java.util.List;

@RequiredArgsConstructor
public class VOCService {

    private final List<VOCSaveHandler> vocSaveHandlers;
    private final CustomRepository customRepository;

    public void saveVoc(VocRegistrationDto dto) {
        for (VOCSaveHandler vocSaveHandler : vocSaveHandlers) {
            if (vocSaveHandler.typeCheck(AttributableType.valueOf(dto.getAttributableType()))) {
                vocSaveHandler.save(dto);
            }
        }
    }

    @Transactional
    public void savePenalty(Long vocId, int penaltyAmount, String penaltyContents) {
        DriversAttributableVOC VOC = customRepository.findDriversAttributableVOCById(vocId);
        TransportationDriver targetDriver = VOC.getAttributableDiver();
        Penalty penalty = new Penalty(penaltyAmount, targetDriver, VOC, penaltyContents);
        customRepository.savePenalty(penalty);
    }

    @Transactional
    public void saveCompensation(Long vocId, String compensationContents, int amount) {
        VoiceOfCustomer voc = customRepository.findVOCById(vocId);
        Compensation compensation = new Compensation(voc, voc.getComplainedClient(), compensationContents, amount);
        customRepository.saveCompensation(compensation);
    }

    @Transactional
    public void checkPenalty(Long penaltyId) {
        customRepository.penaltyChecked(penaltyId);
    }

    public List<CompensationListDto> compensationList(String companyName, Integer maxAmount, Integer minAmount) {
        return customRepository.compensationList(companyName, maxAmount, minAmount);
    }

    public List<VOCDto> vocList(String attributableType) {
        AttributableType type = AttributableType.valueOf(attributableType);
        List<VOCDto> vocDtoList = null;

        switch (type) {
            case TransportDriver:
                vocDtoList = customRepository.findVocListWhenDriversAttributable();
                break;
            case ClientCompany:
                vocDtoList = customRepository.findVocListWhenClientsAttributable();
                break;
            default:
                vocDtoList = customRepository.findVocListDefault();
                break;
        }

        return vocDtoList;
    }
}
