package com.viu;

import com.viu.helpers.DriverHelper;
import com.viu.helpers.ProxyHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException, TimeoutException {
        ProxyHelper.startProxy();
        driver = DriverHelper.getDriverWithProxyConfigured();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
