package teamfresh.assignment.vocregistrationhandler;

import lombok.RequiredArgsConstructor;
import teamfresh.assignment.AttributableType;
import teamfresh.assignment.Repository.CustomRepository;
import teamfresh.assignment.dto.VocRegistrationDto;
import teamfresh.assignment.entity.ClientAttributableVOC;
import teamfresh.assignment.entity.ClientCompany;

// 고객사의 귀책으로 인한 voc 를 등록하는 핸들러
@RequiredArgsConstructor
public class ClientVocHandler implements VOCSaveHandler{

    private final CustomRepository repository;

    @Override
    public boolean typeCheck(AttributableType attributableType) {
        return attributableType == AttributableType.ClientCompany;
    }

    @Override
    public void save(VocRegistrationDto dto) {
        ClientCompany complainedClient = repository.findClientCompanyByCompanyName(dto.getComplainedClient());
        ClientAttributableVOC voc = new ClientAttributableVOC(dto.getVocContents(), complainedClient, dto.getAttributableReason());
        repository.saveClientAttributableVoc(voc);
    }
}
