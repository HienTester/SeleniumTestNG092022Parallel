package hientester.com.pages;

import hientester.com.helpers.PropertiesHelper;
import hientester.com.keywords.WebUI;
import static hientester.com.keywords.WebUI.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    private String URL = "https://crm.anhtester.com/admin/authentication";
    private String PAGETEXT = "Login";

    //Lưu Object của trang Login
    //Dùng đối tượng By trong Selenium để khai báo tên Object cùng giá trị Locator tương ứng
    By headerPage = By.xpath("//h1");
    By inputEmail = By.xpath("//input[@id='email']");
    By inputPassword = By.xpath("//input[@id='password']");
    By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    By messageErroEmail = By.xpath("//div[@class='text-center alert alert-danger']");

       //Viết các hàm xử lý cho trang Login
    public void verifyHeaderPage(){
        Assert.assertEquals(getTextElement(headerPage),PAGETEXT,"FAIL.Header page not match");
    }

    public void verifyErrorMessageDisplay(){
        Assert.assertTrue(getWebElement(messageErroEmail).isDisplayed(),"FAIL.Error message no display");
        Assert.assertEquals(getTextElement(messageErroEmail),"Invalid email or password", "FAIL. Content of the Error message not match.");
    }


    public void enterEmail(String email){
        //driver.findElement(inputEmail).sendKeys(email);
        setText(inputEmail,email);
    }

    public void enterPassword(String password){
        //driver.findElement(inputPassword).sendKeys(password);
        setText(inputPassword,password);
    }

    public void clickOnLoginButton(){
        //driver.findElement(buttonLogin).click();
        clickElement(buttonLogin);
    }


    public DashboardPage login(String email, String password){
        openURL(PropertiesHelper.getValue("url"));
        verifyHeaderPage();
        enterEmail(email);
        //setText(inputEmail,email);
        enterPassword(password);
        //setText(inputPassword,password);
        clickOnLoginButton();
        //clickElement(buttonLogin);

        return new DashboardPage();
    }


    public void loginInvalidEmail(String email, String password){
        openURL(PropertiesHelper.getValue("url"));
        verifyHeaderPage();
        enterEmail(email);
        //setText(inputEmail,email);
        enterPassword(password);
        //setText(inputPassword,password);
        clickOnLoginButton();
        //clickElement(buttonLogin);

        //Kiểm tra thông báo lỗi khi sai email
        verifyErrorMessageDisplay();
    }

}
