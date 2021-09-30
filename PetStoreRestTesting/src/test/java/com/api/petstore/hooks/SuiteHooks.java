package com.api.petstore.hooks;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.api.petstore.wiremock.runner.WiremockServer;

public class SuiteHooks {

    @BeforeSuite(alwaysRun = true, groups = "Wiremock Test Group")
    public void beforeSuiteTests() {
        WiremockServer.startServer();
    }

    @AfterSuite(alwaysRun = true, groups = "Wiremock Test Group")
    public void afterSuiteTests() {
        WiremockServer.resetMappings();
        WiremockServer.stopServer();
    }
}
