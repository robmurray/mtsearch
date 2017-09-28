package com.mt.search.util;

import com.mt.search.service.SimpleFileSearchProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by robertmurray on 9/27/17.
 */
public class SimpleTimer {
    static final Logger LOG = LoggerFactory.getLogger(SimpleFileSearchProviderImpl.class);
    private long startTime;
    private long endTime;

    public SimpleTimer() {
    }

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        if(startTime==0){
            throw new RuntimeException("Timer not started. please call timer.start()");
        }
        endTime = System.nanoTime();
    }

    public long results() {
        long results =(endTime - startTime)/1000000;
        if(LOG.isDebugEnabled()){
            LOG.debug("start time:" + startTime);
            LOG.debug("end time:" + endTime);
            LOG.debug("result:" + results);
        }
        return results;
    }
}
