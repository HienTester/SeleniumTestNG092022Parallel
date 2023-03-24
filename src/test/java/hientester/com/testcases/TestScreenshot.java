package hientester.com.testcases;

import hientester.com.common.BaseTest;
import hientester.com.drivers.DriverManager;
import hientester.com.helpers.CaptureHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class TestScreenshot extends BaseTest {

    @Test
    public void testHomePage1() {
        DriverManager.getDriver().get("https://anhtester.com/login");
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Login | Anh Tester Blog");

    }

    @Test
    public void testHomePage2(Method method) {
        DriverManager.getDriver().get("https://anhtester.com/login");
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Login");

    }

//    @AfterMethod
//    public void tearDown(ITestResult iTestResult) {
//        if (iTestResult.getStatus() == ITestResult.FAILURE) {
//
//            //Chụp màn hình
//            CaptureHelper.captureScreenshot(iTestResult.getName());
//        }
//    }
}

