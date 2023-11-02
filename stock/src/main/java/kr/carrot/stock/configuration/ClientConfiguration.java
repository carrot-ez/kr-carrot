package kr.carrot.stock.configuration;

import kr.carrot.core.configuration.DataGovConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataGovConfiguration.class)
public class ClientConfiguration {
}
