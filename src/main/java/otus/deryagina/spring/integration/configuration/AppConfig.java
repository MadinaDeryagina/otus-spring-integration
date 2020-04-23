package otus.deryagina.spring.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;

@Configuration
public class AppConfig {

    @Bean
    public IntegrationFlow operationChecker(){
        return flow->flow
                .handle("checkAmountService","check")
                .channel("outputChannel");

    }
    @Bean
    public DirectChannel outputChannel(){
        return new DirectChannel();
    }
}
