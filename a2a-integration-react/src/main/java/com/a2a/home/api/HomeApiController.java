
package com.a2a.home.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a.api.AbstractController;
import com.a2a.home.ChartDataItem;
import com.a2a.home.HomeService;

/**
 * REST facade for {@link HomeService} class
 *
 */ 
@Controller
@RequestMapping("/api/home")
public class HomeApiController extends AbstractController {

    private HomeService homeService;
    
    
    @RequestMapping(path = "history", method = RequestMethod.GET)
    public @ResponseBody List<ChartDataItem> history() {
        return homeService.getHistoryChartData();
    }
    
    
    @RequestMapping(path = "summary", method = RequestMethod.GET)
    public @ResponseBody ChartDataItem summary() {
        return homeService.getSummaryChartData();
    }


    @Autowired
    public void setHomeService(HomeService homeService) {
        this.homeService = homeService;
    }
    
}
