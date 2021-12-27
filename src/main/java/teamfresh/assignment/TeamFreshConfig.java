package teamfresh.assignment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import teamfresh.assignment.Repository.*;
import teamfresh.assignment.vocregistrationhandler.ClientVocHandler;
import teamfresh.assignment.vocregistrationhandler.DriverVocHandler;
import teamfresh.assignment.vocregistrationhandler.VOCSaveHandler;

import javax.persistence.EntityManager;
import java.util.ArrayList;

@Configuration
public class TeamFreshConfig {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public VOCService vocService(CustomRepository customRepository) {

        ArrayList<VOCSaveHandler> vocSaveHandlers = new ArrayList<>();
        vocSaveHandlers.add(new DriverVocHandler(customRepository));
        vocSaveHandlers.add(new ClientVocHandler(customRepository));

        return new VOCService(vocSaveHandlers, customRepository);
    }




}
