package otus.deryagina.spring.integration.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import otus.deryagina.spring.integration.domain.Incident;
import otus.deryagina.spring.integration.service.CheckAmountService;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final CheckAmountService checkAmountService;

    @Bean
    public IntegrationFlow operationChecker() {
        return flow -> flow
                .handle(checkAmountService, "check")
                .<Incident, Boolean>route(
                        Incident::isVipSender, mapping -> mapping
                                .subFlowMapping(true, sf -> sf.channel("outputVipChannel")
                                )
                                .subFlowMapping(false, sf -> sf.channel("outputRegularChannel")
                                )

                );


    }

    @Bean
    public DirectChannel outputVipChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel outputRegularChannel() {
        return new DirectChannel();
    }

}
