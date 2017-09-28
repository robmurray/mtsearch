package com.mt.search.dto;

/**
 * Created by robertmurray on 9/27/17.
 */
public class HealthStatusResponse {
    private boolean isHealthy;

    public HealthStatusResponse(boolean isHealthy) {
        this.isHealthy = isHealthy;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }
}
