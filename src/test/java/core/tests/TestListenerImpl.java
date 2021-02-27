package core.tests;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListenerImpl implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest test = (BaseTest) result.getInstance();
        test.makeScreenshotForReport();
    }
}
