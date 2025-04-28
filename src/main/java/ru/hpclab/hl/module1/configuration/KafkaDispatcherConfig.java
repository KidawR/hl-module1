package ru.hpclab.hl.module1.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.kafka.KafkaMessageDispatcher;
import ru.hpclab.hl.module1.service.ArtistService;
import ru.hpclab.hl.module1.service.TicketService;
import ru.hpclab.hl.module1.service.ViewerService;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

@Configuration
public class KafkaDispatcherConfig {

    @Bean
    public KafkaMessageDispatcher kafkaMessageDispatcher(
            ArtistService artistService,
            TicketService ticketService,
            ViewerService viewerService,
            ObjectMapper objectMapper
            //ObservabilityService observabilityService;
    ) {
        return new KafkaMessageDispatcher(
                artistService,
                ticketService,
                viewerService,
                objectMapper
                //observabilityService
        );
    }
}
