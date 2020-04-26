package otus.deryagina.spring.integration.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import otus.deryagina.spring.integration.domain.Operation;

@MessagingGateway
public interface FraudChecker {

    @Gateway(requestChannel = "operationChecker.input" )
    void process(Operation operation);

}
