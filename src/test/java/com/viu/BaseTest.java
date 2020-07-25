package com.viu;

import com.viu.helpers.ProxyHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BaseTest {
    protected WebDriver driver;
    private final ProxyHelper proxyHelper = new ProxyHelper();

    @BeforeSuite
    public void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupBeforeMethod() throws IOException, TimeoutException {
        ChromeOptions options = new ChromeOptions();

        proxyHelper.setProxyIn(options);
        proxyHelper.captureMessagesWithPath("/log");

        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void tearDown() {
        proxyHelper.stopProxy();
        driver.quit();
    }
}
