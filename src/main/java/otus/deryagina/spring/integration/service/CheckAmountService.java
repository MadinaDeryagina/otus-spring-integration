package otus.deryagina.spring.integration.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import otus.deryagina.spring.integration.domain.Incident;
import otus.deryagina.spring.integration.domain.Operation;

import java.util.Date;

@Component
public class CheckAmountService{

    public Incident check(Operation operation) {
        if(operation.getAmount() >= 8){
            return new Incident(operation.getId(), new Date());
        }
        return null;
    }
}
