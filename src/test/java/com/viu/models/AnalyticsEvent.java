package com.viu.models;

import org.json.JSONObject;

public class AnalyticsEvent {
    JSONObject analyticsData;

    private AnalyticsEvent() {
    }

    public AnalyticsEvent(byte[] eventDetails) {
        this.analyticsData = new JSONObject(new String(eventDetails));
    }

    public String getEventName() {
        return getLogInformation().getString("event_name");
    }

    public boolean hasName() {
        return getLogInformation().has("event_name");
    }

    private JSONObject getLogInformation() {
        return analyticsData.getJSONObject("log_info");
    }
}
