package teamfresh.assignment.vocregistrationhandler;

import lombok.RequiredArgsConstructor;
import teamfresh.assignment.AttributableType;
import teamfresh.assignment.Repository.CustomRepository;
import teamfresh.assignment.dto.VocRegistrationDto;
import teamfresh.assignment.entity.ClientCompany;
import teamfresh.assignment.entity.DriversAttributableVOC;
import teamfresh.assignment.entity.TransportationDriver;


// 운송사의 귀책으로 인한 voc 를 등록하는 핸들러
@RequiredArgsConstructor
public class DriverVocHandler implements VOCSaveHandler{

    private final CustomRepository repository;

    @Override
    public boolean typeCheck(AttributableType attributableType) {
        return attributableType == AttributableType.TransportDriver;
    }

    @Override
    public void save(VocRegistrationDto dto) {
        ClientCompany complainedClient = repository.findClientCompanyByCompanyName(dto.getComplainedClient());
        TransportationDriver driver = repository.findTransportDriverByDriverName(dto.getAttributableName());
        DriversAttributableVOC driversAttributableVOC = new DriversAttributableVOC(dto.getVocContents(), complainedClient, dto.getAttributableReason(), driver);
        repository.saveDriversAttributableVOC(driversAttributableVOC);
    }
}
