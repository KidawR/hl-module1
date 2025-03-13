package ru.hpclab.hl.module1.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.model.Artist;
import ru.hpclab.hl.module1.model.Ticket;
import ru.hpclab.hl.module1.model.Viewer;
import ru.hpclab.hl.module1.repository.ArtistRepository;
import ru.hpclab.hl.module1.repository.TicketRepository;
import ru.hpclab.hl.module1.repository.ViewerRepository;
import ru.hpclab.hl.module1.service.ArtistService;
import ru.hpclab.hl.module1.service.StatisticsService;
import ru.hpclab.hl.module1.service.TicketService;
import ru.hpclab.hl.module1.service.ViewerService;

import java.util.Random;
import java.util.UUID;

@Configuration
public class ServicesConfig {

    @Bean
    ViewerService viewerService(ViewerRepository viewerRepository) {
        ViewerService viewerService = new ViewerService(viewerRepository);
        for (int i = 0; i < 5; i++) {
            viewerRepository.save(new Viewer(new Random().nextLong(), "new super user", "new super mail"));
        }
        return viewerService;
    }


    @Bean
    TicketService ticketService(TicketRepository ticketRepository) {
        TicketService bookingService = new TicketService(ticketRepository);
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            ticketRepository.save(new Ticket(r.nextLong(), r.nextLong(),r.nextLong(), "12.10.2024-15.10.2024"));
        }
        return bookingService;
    }

    @Bean
    ArtistService roomService(ArtistRepository artistRepository) {
        ArtistService artistService = new ArtistService(artistRepository);
        for (int i = 0; i < 5; i++) {
            artistRepository.save(new Artist(new Random().nextLong(), "The best groop", Artist.Genre.Roсk,1000));
        }
        return artistService;
    }
    @Bean
    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console2000")
    StatisticsService statisticsService2000(ViewerService viewerService){
        return new StatisticsService(2000, viewerService);
    }

    @Bean
    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console1000")
    StatisticsService statisticsService1000(ViewerService viewerService){
        return new StatisticsService(1000, viewerService);
    }
}
