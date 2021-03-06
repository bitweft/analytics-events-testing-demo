package com.viu.helpers;

import com.viu.models.AnalyticsEvent;

import java.util.List;
import java.util.stream.Collectors;

public class AnalyticsEventsHelper {
    public static List<AnalyticsEvent> getAllActualAnalyticsEvents() {
        String analyticsMessagesUrl = "https://um.viuapi.io/loggingservice/browser/api/log";

        return ProxyHelper.getInterceptedMessages().stream()
                .filter(message -> message.getRequest().getUrl().equals(analyticsMessagesUrl))
                .filter(message -> message.getRequest().getBody().length != 0)
                .map(message -> new AnalyticsEvent(message.getRequest().getBody()))
                .filter(AnalyticsEvent::hasName)
                .collect(Collectors.toList());
    }
}
