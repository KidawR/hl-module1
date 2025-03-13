package ru.hpclab.hl.module1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ViewerServiceTest.ViewerServiceTestConfiguration.class})
public class ViewerServiceTest {

    @Autowired
    private ViewerService viewerService;

    @Autowired
    private ViewerRepository viewerRepository;

    @Test
    public void testCreateAndGet(){
        //create
        Viewer viewer = new Viewer(UUID.randomUUID(), "name");

        Viewer savedViewer = viewerService.saveUser(viewer);

        Assertions.assertEquals(viewer.getFio(), savedViewer.getFio());
        Mockito.verify(viewerRepository, Mockito.times(1)).save(viewer);

        //getAll
        List<Viewer> userList = viewerService.getAllViewers();

        Assertions.assertEquals("name1", userList.get(0).getFio());
        Assertions.assertEquals("name2", userList.get(1).getFio());
        Mockito.verify(viewerRepository, Mockito.times(1)).findAll();

    }

    @Configuration
    static class ViewerServiceTestConfiguration {

        @Bean
        ViewerRepository viewerRepository() {
            ViewerRepository viewerRepository = mock(ViewerRepository.class);
            when(viewerRepository.save(any())).thenReturn(new Viewer(UUID.randomUUID(), "name"));
            when(viewerRepository.findAll())
                    .thenReturn(Arrays.asList(new Viewer(UUID.randomUUID(), "name1"),
                            new Viewer(UUID.randomUUID(), "name2")));
            return viewerRepository;
        }

        @Bean
        ViewerService UserService(ViewerRepository userRepository){
            return new ViewerService(userRepository);
        }
    }

}
