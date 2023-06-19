package hientester.com.listeners;

import com.aventstack.extentreports.Status;
import hientester.com.helpers.CaptureHelper;
import hientester.com.helpers.PropertiesHelper;
import hientester.com.reports.ExtentReportManager;
import hientester.com.reports.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }


    @Override
    public void onStart(ITestContext result) {
        PropertiesHelper.loadAllFiles();
        //Khởi tạo reports (Extend và Allure)

    }

    @Override
    public void onFinish(ITestContext result) {
        // Đóng reports
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test case: " + result.getName());

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test case: " + result.getName() + "is passed");

        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }


    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test case: " + result.getName() + " is failed.");
        //Screenshot sau khi bị fail
        CaptureHelper.captureScreenshot(result.getName());
        System.out.println(result.getThrowable().toString());

        //Extent Report
        ExtentTestManager.addScreenShot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test case: " + result.getName() + "is skipped");
        System.out.println(result.getThrowable().toString());

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Đây là test case bị Fail nhưng có phần Success: " + result.getName());
        System.out.println(result.getThrowable().toString());
    }
}
