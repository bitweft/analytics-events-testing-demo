package com.viu;

import com.viu.helpers.AnalyticsEventsHelper;
import com.viu.pages.HomePage;
import org.testng.annotations.Test;

import java.util.List;


public class VideoStreamTest extends BaseTest {
    @Test
    public void shouldBeAbleToStreamVideo() {
        new HomePage(driver)
                .selectVideoAtPosition(0)
                .play();

        List<String> expectedEvents = List.of("APP_LAUNCH", "page_view", "video_start", "video_stream_raw");
        List<String> actualAnalyticsEvents = AnalyticsEventsHelper.getAnalyticsEventNames();
        assert actualAnalyticsEvents.containsAll(expectedEvents);
    }
}
