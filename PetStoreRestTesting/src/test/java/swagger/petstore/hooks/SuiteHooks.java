package swagger.petstore.hooks;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import swagger.petstore.wiremock.runner.WiremockServer;

public class SuiteHooks {

    @BeforeSuite(alwaysRun = true, groups = "Wiremock Test Group")
    public void beforeSuiteTests() {
        WiremockServer.startServer();
    }

    @AfterSuite(alwaysRun = true, groups = "Wiremock Test Group")
    public void afterSuiteTests() {
        WiremockServer.stopServer();
    }
}
