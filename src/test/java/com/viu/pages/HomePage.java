package com.viu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final String homeUrl = "https://www.viu.com/ott/in/en/hindi";
    private final By videoThumbnails = By.cssSelector(".c-contentslider__griditem a");
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 20);
        driver.get(homeUrl);
    }

    public VideoPlayerPage selectVideoAtPosition(int position) {
        wait.until(ExpectedConditions.presenceOfElementLocated(videoThumbnails));
        driver.findElements(videoThumbnails).get(position).click();
        return new VideoPlayerPage(driver);
    }
}
