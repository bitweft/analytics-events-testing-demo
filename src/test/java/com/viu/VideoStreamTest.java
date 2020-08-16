package com.viu;

import com.viu.helpers.AnalyticsEventsVerifier;
import com.viu.helpers.DriverHelper;
import com.viu.helpers.ProxyHelper;
import com.viu.models.AnalyticsEvent;
import com.viu.models.ExpectedEventDetails;
import com.viu.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static com.viu.helpers.AnalyticsEventsHelper.getAllActualAnalyticsEvents;
import static com.viu.helpers.AnalyticsExpectationsHelper.getExpectedEventDetailsFrom;


public class VideoStreamTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException, TimeoutException {
        ProxyHelper.startProxy();
        driver = DriverHelper.getDriverWithProxyConfigured();
    }

    @Test
    public void shouldBeAbleToStreamVideo() throws InterruptedException {
        new HomePage(driver)
                .selectVideoAtPosition(0)
                .play();

        ProxyHelper.stopProxy();
        verifyAnalyticsEvents();
    }

    private void verifyAnalyticsEvents() {
        List<ExpectedEventDetails> expectedEventDetails = getExpectedEventDetailsFrom("VideoStreamExpectedEvents.json");
        List<AnalyticsEvent> allActualAnalyticsEvents = getAllActualAnalyticsEvents();
        AnalyticsEventsVerifier.verify(expectedEventDetails, allActualAnalyticsEvents);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
