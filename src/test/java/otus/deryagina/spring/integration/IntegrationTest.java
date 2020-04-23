package otus.deryagina.spring.integration;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.test.context.MockIntegrationContext;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import otus.deryagina.spring.integration.configuration.AppConfig;
import otus.deryagina.spring.integration.domain.Operation;
import otus.deryagina.spring.integration.gateway.FraudChecker;

@SpringBootTest
@ContextConfiguration(classes = {Main.class,AppConfig.class,FraudChecker.class, DirectChannel.class, IntegrationFlow.class})
public class IntegrationTest {

    @Autowired
    private DirectChannel outputChannel;

    @Autowired
    private FraudChecker fraudChecker;

    private Operation goodOperation;

    @BeforeEach
    void init(){
        goodOperation = new Operation(1,"a","b",10);
    }
    @Test
    public void testIntegration(){
  //      System.out.println(mockIntegrationContext);
        System.out.println(fraudChecker);
        System.out.println(outputChannel);
        //fraudChecker.process(goodOperation);
        outputChannel.subscribe(x-> System.out.println(x));
    }
}
