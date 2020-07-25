package com.viu.helpers;

import io.appium.mitmproxy.InterceptedMessage;
import io.appium.mitmproxy.MitmproxyJava;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class ProxyHelper {
    private MitmproxyJava proxy;
    private List<InterceptedMessage> interceptedMessages;
    private final String mitmproxyPath = "/usr/local/bin/mitmdump";

    public void setProxyIn(ChromeOptions options) {
        Proxy manualProxy = new Proxy();
        manualProxy.setHttpProxy("localhost:8080");
        manualProxy.setSslProxy("localhost:8080");

        options.setProxy(manualProxy);
        options.setCapability("proxy", manualProxy);
        options.setAcceptInsecureCerts(true);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    }

    public void captureMessagesWithPath(String urlPath) throws IOException, TimeoutException {
        interceptedMessages = new ArrayList<>();

        proxy = new MitmproxyJava(mitmproxyPath, (InterceptedMessage message) -> {
            conditionallyCaptureMessage(urlPath, message);
            return message;
        });
        proxy.start();
    }

    private void conditionallyCaptureMessage(String urlPath, InterceptedMessage message) {
        if (message.getRequest().getUrl().contains(urlPath)) {
            interceptedMessages.add(message);
        }
    }

    public void stopProxy() {
        if (proxy != null) {
            try {
                proxy.stop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
