package com.viu;

import com.viu.helpers.DriverHelper;
import com.viu.helpers.ProxyHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void initialSetup() throws IOException, TimeoutException {
        ProxyHelper.startProxy();
        driver = DriverHelper.getDriverWithProxyConfigured();
    }

    @BeforeMethod
    public void setup() {
        ProxyHelper.clearMessages();
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        ProxyHelper.stopProxy();
        driver.quit();
    }
}
