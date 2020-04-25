package otus.deryagina.spring.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import otus.deryagina.spring.integration.domain.Operation;
import otus.deryagina.spring.integration.gateway.FraudChecker;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationTest {

    @Autowired
    private DirectChannel outputVipChannel;

    @Autowired
    private DirectChannel outputRegularChannel;

    @Autowired
    private FraudChecker fraudChecker;

    private Operation badOperation;
    private Operation badVipOperation;

    @BeforeEach
    void init(){
        badOperation = new Operation(1,"a","b",10);
        badVipOperation = new Operation(2,"vip","b",100);
    }

    @Test
    public void testIntegrationWithBadOperation(){
        outputRegularChannel.subscribe(x->  assertThat(x.getPayload()).hasFieldOrPropertyWithValue("operationId",1L));
        fraudChecker.process(badOperation);

    }

    @Test
    public void testIntegrationWithBadOperationForVipClient(){
        outputVipChannel.subscribe(x->  assertThat(x.getPayload()).hasFieldOrPropertyWithValue("operationId",2L));
        fraudChecker.process(badVipOperation);
    }

}
