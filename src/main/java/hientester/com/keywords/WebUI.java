package hientester.com.keywords;

import com.aventstack.extentreports.Status;
import hientester.com.drivers.DriverManager;
import hientester.com.reports.ExtentTestManager;
import hientester.com.utils.LogUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static hientester.com.drivers.DriverManager.*;

public class WebUI {

    private static int EXPILICIT_WAIT_TIMEOUT = 10;
    private static int WAIT_PAGE_LEADED_TIMEOUT = 30;

     public static WebElement getWebElement(By by) {
        return getDriver().findElement(by);
    }

    public static void logConsole(String mesage) {
        System.out.println(mesage);
    }

    public static void hoverOnElement(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(getDriver());
        action.moveToElement(getWebElement(by));
        LogUtils.info("Hover on element " + by);
        ExtentTestManager.logMessage(Status.PASS,"Hover on element  + by");
    }

    public static WebElement highLightElement(By by) {
        waitForElementVisible(by);
        //Tô màu border ngoài chính element chỉ định - màu đỏ ( có thể đổi màu khác)
        if (getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor)getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

    public static void rightClickElement(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(getDriver());
        action.contextClick(getWebElement(by));
        LogUtils.info("Right click on Element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Right click on Element " + by);
    }


    public static void openURL(String URL) {
        getDriver().get(URL);
        waitForPageLoaded();
        LogUtils.info("Open URL: " + URL);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + URL);
    }

    public static String getCurrentURL() {
        waitForPageLoaded();
        LogUtils.info("Get Current URL: " + getDriver().getCurrentUrl());
        ExtentTestManager.logMessage(Status.PASS, "Get Current URL: " + getDriver().getCurrentUrl());
        return DriverManager.getDriver().getCurrentUrl();

    }

    public static void clickElement(By by) {
        waitForElementVisible(by);
        highLightElement(by);
        getWebElement(by).click();
        LogUtils.info("Click on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Click on element " + by);
    }

    public static void setText(By by, String value) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value);
        LogUtils.info("Set Text " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set Text " + value + " on element " + by);
    }

    public static String getTextElement(By by) {
        waitForElementVisible(by);
        LogUtils.info("Get text of element " + by);
        LogUtils.info("==>Text: " + getWebElement(by).getText());
        ExtentTestManager.logMessage(Status.PASS, "Get text of element " + by);
        ExtentTestManager.logMessage(Status.INFO, "==>Text: " + getWebElement(by).getText());
        return getWebElement(by).getText();

    }

    public static String getAttributeElement(By by, String attributename) {
        waitForElementVisible(by);
        LogUtils.info("Get attribute value of element " + by);
        LogUtils.info("==>Attribute value: " + getWebElement(by).getAttribute(attributename));
        ExtentTestManager.logMessage(Status.PASS, "Get attribute value of element " + by);
        ExtentTestManager.logMessage(Status.INFO, "==>Attribute value: " + getWebElement(by).getAttribute(attributename));
        return getWebElement(by).getAttribute(attributename);
    }

    public static void scrollToElementWithJS(By by) {
        waitForElementPresent(by);
        //Dùng Action class
        //Robot class
        //Dùng JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("argument[0].scrollIntoView(true);", getWebElement(by));
        logConsole("Scroll to element " + by);
    }

    public static void scrollToElement(By by) {
        //Dùng Action class
        //Dùng JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("argument[0].scrollIntoView(true);", getWebElement(by));
    }

    public static void scrollToElementWithRobot(By by) {

    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPILICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public static void waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPILICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    public static void waitForElementClickable(By by, int second) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }


    public static boolean verifyElementVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean verifyElementNotVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean verifyElementNotPresent(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return false;
        } catch (Exception e) {
            return true;
        }
    }


    public static boolean checkElementExist(By by) {
        List<WebElement> listElement = getDriver().findElements(by);

        if (listElement.size() > 0) {
            System.out.println("Element " + by + " existing.");
            return true;
        } else {
            System.out.println("Element " + by + " NOT exist.");
            return false;
        }
    }


    public static Boolean checkElementExist(String xpath) {
        List<WebElement> listElement = getDriver().findElements(By.xpath(xpath));

        if (listElement.size() > 0) {
            System.out.println("Element " + xpath + " existing.");
            return true;
        } else {
            System.out.println("Element " + xpath + " NOT exist.");
            return false;
        }
    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_PAGE_LEADED_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }
}
