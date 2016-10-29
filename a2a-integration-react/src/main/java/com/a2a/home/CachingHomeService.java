package com.a2a.home;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.a2a.home.repository.HomeRepository;


/**
 * This implementation of {@link HomeService} uses TODO
 *
 */
@Service
@Transactional
public class CachingHomeService implements HomeService {

    private HomeRepository repository;
    
    /* *** cached data *** */
    private List<ChartDataItem> cachedHistoryData;
    
    private ChartDataItem cachedSummaryData;
    
    
    /**
     * @see com.a2a.home.HomeService#calcStatistics()
     */
    @Override
    public synchronized void calcStatistics() {
        List<ChartDataItem> chartData = repository.fetchChartData();
        
        long processed = 0;
        long failed = 0;
        long rejected = 0;
        long other = 0;
        
        for (ChartDataItem item : chartData) {
            processed += item.getProcessed(); 
            failed += item.getFailed(); 
            rejected += item.getRejected(); 
            other += item.getOther(); 
        }
        
        cachedHistoryData = Collections.unmodifiableList(chartData);
        cachedSummaryData = new ChartDataItem(processed, failed, rejected, other);
    }

    /**
     * @return
     * @see com.a2a.home.HomeService#getHistoryChartData()
     */
    @Override
    public List<ChartDataItem> getHistoryChartData() {
        if (cachedHistoryData == null) {
            return Collections.emptyList();
        }
        return cachedHistoryData;
    }

    /**
     * @return
     * @see com.a2a.home.HomeService#getSummaryChartData()
     */
    @Override
    public ChartDataItem getSummaryChartData() {
        return cachedSummaryData;
    }

    @Autowired
    public void setRepository(HomeRepository repository) {
        this.repository = repository;
    }


}
