package ru.hpclab.hl.module1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

public class StatisticsService {


    @Value("${statisticsservice.infostring:lines}")
    private String infoString;

    final int delay;

    private final ViewerService viewerService;

    public StatisticsService(int delay, ViewerService viewerService) {
        this.delay = delay;
        this.viewerService = viewerService;
    }

    @Async(value = "applicationTaskExecutor")
    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " - Fixed rate task async - "+ delay + " - " + infoString + " - "
                        + viewerService.getAllViewers().size());
        Thread.sleep(delay);
    }
}
