package otus.deryagina.spring.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import otus.deryagina.spring.integration.domain.Operation;
import otus.deryagina.spring.integration.gateway.FraudChecker;

@EnableIntegration
@IntegrationComponentScan
@Configuration
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class);

        DirectChannel outputChannel = ctx.getBean("outputVipChannel", DirectChannel.class);
        outputChannel.subscribe(s -> System.out.println("RESULT: " + s));

        FraudChecker fraudChecker = ctx.getBean(FraudChecker.class);
        fraudChecker.process(new Operation(1, "vip", "b", 10));

        ctx.close();

    }
}
