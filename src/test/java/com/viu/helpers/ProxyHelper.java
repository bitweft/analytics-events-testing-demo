package com.viu.helpers;

import io.appium.mitmproxy.InterceptedMessage;
import io.appium.mitmproxy.MitmproxyJava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class ProxyHelper {
    private static MitmproxyJava proxy;
    private static final List<InterceptedMessage> interceptedMessages = new ArrayList<>();
    private static final String mitmProxyPath = "/Library/Frameworks/Python.framework/Versions/3.8/bin/mitmdump";

    public static void startProxy() throws IOException, TimeoutException {
        clearMessages();

        proxy = new MitmproxyJava(mitmProxyPath, (InterceptedMessage message) -> {
            interceptedMessages.add(message);
            return message;
        });
        proxy.start();
    }

    public static void stopProxy() throws InterruptedException {
        proxy.stop();
    }

    public static List<InterceptedMessage> getInterceptedMessages() {
        return interceptedMessages;
    }

    private static void clearMessages() {
        interceptedMessages.clear();
    }
}
