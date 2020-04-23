package otus.deryagina.spring.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import otus.deryagina.spring.integration.domain.Operation;
import otus.deryagina.spring.integration.gateway.FraudChecker;

@EnableIntegration
@IntegrationComponentScan
@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {

        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);

        DirectChannel outputChannel= ctx.getBean("outputChannel",DirectChannel.class);
        outputChannel.subscribe(s-> System.out.println(s));

        FraudChecker fraudChecker = ctx.getBean(FraudChecker.class);
        fraudChecker.process(new Operation(1,"a", "b", 10));

        ctx.close();

    }
}
