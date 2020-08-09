package com.viu.models;

import org.json.JSONObject;

public class AnalyticsEvent {
    JSONObject analyticsData;

    public AnalyticsEvent(byte[] eventDetails) {
        this.analyticsData = new JSONObject(new String(eventDetails));
    }

    public boolean hasName() {
        return getLogInfo().has("event_name");
    }

    public String getName() {
        return getLogInfo().getString("event_name");
    }

    private JSONObject getLogInfo() {
        return analyticsData.getJSONObject("log_info");
    }

    public boolean hasEventKey(String key) {
        return getEventDetails().has(key);
    }

    public String getEventValue(String key) {
        return getEventDetails().getString(key);
    }

    private JSONObject getEventDetails() {
        return analyticsData.getJSONObject("event_info").getJSONObject("event");
    }
}
