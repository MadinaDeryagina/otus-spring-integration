package otus.deryagina.spring.integration.service;

import org.springframework.stereotype.Component;
import otus.deryagina.spring.integration.domain.Incident;
import otus.deryagina.spring.integration.domain.Operation;

import java.util.Date;

@Component
public class CheckAmountService{

    public Incident check(Operation operation) {
        if(operation.getAmount() >= 8){
            boolean isVipSender = false;
            if(operation.getSender().equals("vip")) {
                isVipSender = true;
            }
            return new Incident(operation.getId(),isVipSender, new Date());
        }
        return null;
    }
}
