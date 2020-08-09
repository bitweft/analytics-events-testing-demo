package com.viu.helpers;

import com.viu.models.AnalyticsEvent;
import com.viu.models.ExactMatch;
import com.viu.models.ExpectedEventDetails;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsEventsVerifier {
    public static void verify(List<ExpectedEventDetails> expectedEventDetails, List<AnalyticsEvent> allActualAnalyticsEvents) {
        List<EventsPair> pairs = pairCorrespondingEvents(expectedEventDetails, allActualAnalyticsEvents);
        pairs.forEach(pair -> verifyDetails(pair.getExpectedEventDetails(), pair.getActualEvent()));
    }

    private static void verifyDetails(ExpectedEventDetails expectedEventDetails, AnalyticsEvent actualEvent) {
        verifyPresenceOfKeys(expectedEventDetails, actualEvent);
        verifyExactMatches(expectedEventDetails, actualEvent);
    }

    private static void verifyPresenceOfKeys(ExpectedEventDetails expectedEventDetails, AnalyticsEvent actualEvent) {
        List<String> expectedEventKeys = expectedEventDetails.keysPresenceMatch();
        assert expectedEventKeys.stream().allMatch(actualEvent::hasEventKey);
    }

    private static void verifyExactMatches(ExpectedEventDetails expectedEventDetails, AnalyticsEvent actualEvent) {
        List<ExactMatch> expectedExactMatch = expectedEventDetails.exactMatch();
        assert expectedExactMatch.stream().allMatch(e -> actualEvent.getEventValue(e.key()).equals(e.value()));
    }

    public static List<EventsPair> pairCorrespondingEvents(List<ExpectedEventDetails> expectedEventDetails, List<AnalyticsEvent> allEvents) {
        List<EventsPair> pairs = new ArrayList<>();
        int expectedEventPosition = 0;

        for (int i = 0; i < allEvents.size() && expectedEventPosition < expectedEventDetails.size(); i++) {
            ExpectedEventDetails expectedEvent = expectedEventDetails.get(expectedEventPosition);
            AnalyticsEvent event = allEvents.get(i);
            if (event.getName().equals(expectedEvent.eventName())) {
                pairs.add(new EventsPair(expectedEvent, event));
                expectedEventPosition++;
            }
        }
        return pairs;
    }
}


class EventsPair {
    private final ExpectedEventDetails expectedEventDetails;
    private final AnalyticsEvent actualEvent;

    EventsPair(ExpectedEventDetails expectedEventDetails, AnalyticsEvent actualEvent) {
        this.expectedEventDetails = expectedEventDetails;
        this.actualEvent = actualEvent;
    }

    public ExpectedEventDetails getExpectedEventDetails() {
        return expectedEventDetails;
    }

    public AnalyticsEvent getActualEvent() {
        return actualEvent;
    }
}