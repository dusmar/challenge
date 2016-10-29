package com.a2a.home;


/*
NOT_PROCESSED("not_processed"), PROCESSED("processed"), FAILED("failed"), INHIBITED("inhibited"), REJECTED(
            "rejected"), TESTED("tested"), BLOCKED("blocked")
 */
public class ChartDataItem {

    private Long processed = 0L;
    
    private Long failed = 0L;
    
    private Long rejected = 0L;
    
    private Long other = 0L;
    
    private String statDate;
    
    public ChartDataItem() {
        
    }
    
    /**
     * @param processed
     * @param failed
     * @param blocked
     * @param notProcessed
     */
    public ChartDataItem(Long processed, Long failed, Long blocked, Long notProcessed) {
        this(null, processed, failed, blocked, notProcessed);
    }
    
    public ChartDataItem(String statDate, Long processed, Long failed, Long rejected, Long notProcessed) {
        this.statDate = statDate;
        this.processed = toLong(processed);
        this.failed = toLong(failed);
        this.rejected = toLong(rejected);
        this.other = toLong(notProcessed);
    }        
    
    public Long getProcessed() {
        return processed;
    }

    public Long getFailed() {
        return failed;
    }

    public Long getRejected() {
        return rejected;
    }

    public Long getOther() {
        return other;
    }

    public String getStatDate() {
        return statDate;
    }

    public void setProcessed(Number processed) {
        this.processed = toLong(processed);
    }

    public void setFailed(Number failed) {
        this.failed = toLong(failed);
    }

    public void setRejected(Number rejected) {
        this.rejected = toLong(rejected);
    }

    public void setOther(Number other) {
        this.other = toLong(other);
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    
    private long toLong(Number n) {
        return n == null ? 0L : n.longValue();
    }
}
