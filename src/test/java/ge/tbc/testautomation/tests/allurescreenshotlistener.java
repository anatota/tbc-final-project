package ge.tbc.testautomation.tests;

import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.ITestListener;

public class allurescreenshotlistener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        if (testInstance instanceof BaseTest base && base.page != null) {
            try {
                byte[] screenshot = base.page.screenshot();
                System.out.println("screenshot captured for failed test: " + result.getName());
                Allure.getLifecycle().addAttachment(
                        "failure-screenshot", "image/png", "png", screenshot
                );
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Object testInstance = result.getInstance();
        if (testInstance instanceof BaseTest base && base.page != null) {
            try {
                byte[] screenshot = base.page.screenshot();
                System.out.println("screenshot captured for skipped test: " + result.getName());
                Allure.getLifecycle().addAttachment(
                        "skipped-screenshot", "image/png", "png", screenshot
                );
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot: " + e.getMessage());
            }
        }
    }
}