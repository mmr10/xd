package eu.els.sie.xmlfirst.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by mmarzougui on 14/02/2017.
 */
@ConfigurationProperties(prefix = "batch", ignoreUnknownFields = false)
public class BatchProperties {

    private int pageSize;

    private int threadPoolSize ;

    private int throttleLimit ;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public int getThrottleLimit() {
        return throttleLimit;
    }

    public void setThrottleLimit(int throttleLimit) {
        this.throttleLimit = throttleLimit;
    }
}
