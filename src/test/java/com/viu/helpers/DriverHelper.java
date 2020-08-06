package com.viu.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class DriverHelper {
    public static WebDriver getDriverWithProxyConfigured() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = getChromeOptionsWithProxyConfigured();
        return new ChromeDriver(options);
    }

    private static ChromeOptions getChromeOptionsWithProxyConfigured() {
        Proxy proxy = getProxy();

        ChromeOptions options = new ChromeOptions();
        options.setProxy(proxy);
        options.setCapability("proxy", proxy);
        options.setAcceptInsecureCerts(true);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        return options;
    }

    private static Proxy getProxy() {
        String localhostProxyUrl = "localhost:8080";
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(localhostProxyUrl);
        proxy.setSslProxy(localhostProxyUrl);
        return proxy;
    }
}
