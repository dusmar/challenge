package com.a2a.home;

import java.util.List;


public interface HomeService {

    void calcStatistics();
    
    List<ChartDataItem> getHistoryChartData();
    
    ChartDataItem getSummaryChartData();
    
}
