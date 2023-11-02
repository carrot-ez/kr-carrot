package kr.carrot.stock.configuration;

import kr.carrot.core.configuration.DataGovConfiguration;
import kr.carrot.core.configuration.RestTemplateConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataGovConfiguration.class, RestTemplateConfiguration.class})
public class ClientConfiguration {
}
