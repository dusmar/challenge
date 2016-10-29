package com.a2a.home;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HomeStatsJob {

    private HomeService homeService;

    
    @PostConstruct
    @Scheduled(cron = "${home.stats.cron:0 */5 * * * *}")
    public void calcStatistics() {
        homeService.calcStatistics();
    }
    
    
    @Autowired
    public void setHomeService(HomeService homeService) {
        this.homeService = homeService;
    }
    
    
    

}
