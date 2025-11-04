package ge.tbc.testautomation.tests;
         import io.qameta.allure.Allure;
         import org.testng.ITestResult;
         import org.testng.ITestListener;
         import java.io.ByteArrayInputStream;

         public class allurescreenshotlistener implements ITestListener {
             @Override
             public void onTestFailure(ITestResult result) {
                 Object testInstance = result.getInstance();
                 if(testInstance instanceof BaseTest base && base.page != null) {
                     try {
                         byte[] screenshot = base.page.screenshot();
                         System.out.println("screenshot captured for failed test: " + result.getName());
                         Allure.getLifecycle().addAttachment(
                             "failure-screenshot", "image/png", "png", screenshot
                         );
                     } catch(Exception e) {
                         System.out.println("Exception while taking screenshot: " + e.getMessage());
                     }
                 }
             }
             @Override
                public void onTestStart(ITestResult result) {//take screenshot on test start
                 Object testInstance = result.getInstance();
                    if(testInstance instanceof BaseTest base && base.page != null) {
                        try {
                            byte[] screenshot = base.page.screenshot();
                            System.out.println("screenshot captured for started test: " + result.getName());
                            Allure.getLifecycle().addAttachment(
                                "start-screenshot", "image/png", "png", screenshot
                            );
                        } catch(Exception e) {
                            System.out.println("Exception while taking screenshot: " + e.getMessage());
                        }
                    }

             }
             @Override
                public void onTestSuccess(ITestResult result) {
                    Object testInstance = result.getInstance();
                    if(testInstance instanceof BaseTest base && base.page != null) {
                        try {
                            byte[] screenshot = base.page.screenshot();
                            System.out.println("screenshot captured for passed test: " + result.getName());
                            Allure.getLifecycle().addAttachment(
                                "success-screenshot", "image/png", "png", screenshot
                            );
                        } catch(Exception e) {
                            System.out.println("Exception while taking screenshot: " + e.getMessage());
                        }
                    }
                }
             @Override
                public void onTestSkipped(ITestResult result) {
                    Object testInstance = result.getInstance();
                    if(testInstance instanceof BaseTest base && base.page != null) {
                        try {
                            byte[] screenshot = base.page.screenshot();
                            System.out.println("screenshot captured for skipped test: " + result.getName());
                            Allure.getLifecycle().addAttachment(
                                "skipped-screenshot", "image/png", "png", screenshot
                            );
                        } catch(Exception e) {
                            System.out.println("Exception while taking screenshot: " + e.getMessage());
                        }
                    }
                }
         }