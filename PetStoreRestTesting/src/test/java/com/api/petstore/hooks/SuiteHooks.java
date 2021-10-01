package com.api.petstore.hooks;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.api.petstore.wiremock.runner.WiremockServer;

import static com.api.petstore.instances.TestingGroups.WIREMOCK_GROUP;

public class SuiteHooks {

    @BeforeSuite(alwaysRun = true, groups = WIREMOCK_GROUP)
    public void beforeSuiteTests() {
        WiremockServer.startServer();
    }

    @AfterSuite(alwaysRun = true, groups = WIREMOCK_GROUP)
    public void afterSuiteTests() {
        WiremockServer.resetMappings();
        WiremockServer.stopServer();
    }
}