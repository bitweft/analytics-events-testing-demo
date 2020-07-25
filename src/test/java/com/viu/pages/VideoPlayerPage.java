package com.viu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoPlayerPage {
    private final WebDriver driver;
    private final By playButton = By.className("CN-playBtn");
    private final WebDriverWait wait;

    public VideoPlayerPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 20);
    }

    public void play() {
        wait.until(ExpectedConditions.presenceOfElementLocated(playButton));
        driver.findElement(playButton).click();
    }
}
