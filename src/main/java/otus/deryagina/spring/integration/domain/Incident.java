package otus.deryagina.spring.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Incident {
    private long operationId;
    private boolean isVipSender;
    private Date date;

}
