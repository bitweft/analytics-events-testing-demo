package com.viu;

import com.viu.pages.HomePage;
import org.testng.annotations.Test;

public class VideoStreamTest extends BaseTest {
    @Test
    public void shouldBeAbleToStreamVideo() {
        new HomePage(driver)
                .selectVideoAtPosition(0)
                .play();
    }
}
