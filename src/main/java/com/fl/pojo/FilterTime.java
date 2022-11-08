package com.fl.pojo;

public class FilterTime {
    private long startTime;
    private long endTime;

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getSubTime()
    {
        return getEndTime()-getStartTime();
    }
}
