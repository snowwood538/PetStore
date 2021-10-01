package com.api.petstore.wiremock.runner;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import static com.api.petstore.swagger.instances.urls.BaseUrls.PORT_MOCK;

public class WiremockServer {
    private static WireMockServer server = new WireMockServer(PORT_MOCK);

    public static void startServer() {
        server.start();
    }

    public static void stopServer() {
        server.stop();
    }

    public static void resetMappings() {
        server.resetMappings();
    }

    public static void cleanMock(StubMapping stub) {
        server.removeStubMapping(stub);
    }
}