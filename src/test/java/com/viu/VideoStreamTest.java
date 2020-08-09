package com.viu;

import com.viu.helpers.AnalyticsEventsVerifier;
import com.viu.models.AnalyticsEvent;
import com.viu.models.ExpectedEventDetails;
import com.viu.pages.HomePage;
import org.testng.annotations.Test;

import java.util.List;

import static com.viu.helpers.AnalyticsEventsHelper.getAllActualAnalyticsEvents;
import static com.viu.helpers.AnalyticsExpectationsHelper.getExpectedEventDetailsFrom;


public class VideoStreamTest extends BaseTest {
    @Test
    public void shouldBeAbleToStreamVideo() {
        new HomePage(driver)
                .selectVideoAtPosition(0)
                .play();

        verifyAnalyticsEvents();
    }

    private void verifyAnalyticsEvents() {
        List<ExpectedEventDetails> expectedEventDetails = getExpectedEventDetailsFrom("VideoStreamExpectedEvents.json");
        List<AnalyticsEvent> allActualAnalyticsEvents = getAllActualAnalyticsEvents();
        AnalyticsEventsVerifier.verify(expectedEventDetails, allActualAnalyticsEvents);
    }
}
