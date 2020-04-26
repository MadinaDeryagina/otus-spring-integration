package otus.deryagina.spring.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Operation {
    private long id;
    private String sender;
    private String receiver;
    private double amount;

}
