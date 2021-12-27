package teamfresh.assignment.vocregistrationhandler;


import teamfresh.assignment.AttributableType;
import teamfresh.assignment.dto.VocRegistrationDto;

public interface VOCSaveHandler {

    boolean typeCheck(AttributableType attributableType);

    void save(VocRegistrationDto vocRegistrationDto);

}
