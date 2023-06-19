package hientester.com.testcases;

import hientester.com.common.BaseTest;
import hientester.com.dataproviders.DataLogin;
import hientester.com.drivers.DriverManager;
import hientester.com.helpers.CaptureHelper;
import hientester.com.helpers.ExcelHelper;
import hientester.com.helpers.PropertiesHelper;
import hientester.com.keywords.WebUI;
import hientester.com.listeners.TestListener;
import hientester.com.pages.LoginPage;
import hientester.com.utils.LogUtils;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Hashtable;


public class LoginTest extends BaseTest {

    LoginPage loginPage;

//    @BeforeClass
//    public void setupClass(){
//        CaptureHelper.startRecord("LoginTest");
//    }
//
//    @AfterClass
//    public void tearDownClass(){
//        CaptureHelper.stopRecord();
//    }


    @Test
    public void loginTestSuccess1() {
        //Khởi tạo đối tượng trang LoginPage
        //Truyền driver từ BaseTest
        loginPage = new LoginPage();

        //Gọi hàm "login" từ LoginPage để dùng
        loginPage.login(PropertiesHelper.getValue("email"), PropertiesHelper.getValue("password"));

        PropertiesHelper.setFile("src/test/resources/configs/data.properties");
        PropertiesHelper.setValue("label", WebUI.getTextElement(By.xpath("//span[normalize-space()='Invoice overview']")));
    }

    @Test
    public void loginTestSuccess2() {
        //Khởi tạo đối tượng trang LoginPage
        //Truyền driver từ BaseTest
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/datatest/CRM.xlsx","Login");

        //Gọi hàm "login" từ LoginPage để dùng
        loginPage.login(excelHelper.getCellData("EMAIL",1), excelHelper.getCellData("PASSWORD",1));

        //Chạy tới dòng này nghĩa là Passed
        excelHelper.setCellData("Passed",1,"RESULT");
    }

    @Test(dataProvider = "dataProviderLoginCRM", dataProviderClass = DataLogin.class)
    public void loginTest3(String email, String password) {
        loginPage = new LoginPage();
        loginPage.loginInvalidEmail("admin@example123.com", "123456");
    }

    @Test(dataProvider = "data_provider_login_excel", dataProviderClass = DataLogin.class)
    public void loginTestFromDataProviderReadExcel(String email, String password, String result) {
        loginPage = new LoginPage();
        loginPage.login(email, password);
    }

    @Test(dataProvider = "data_provider_login_excel_custom_row", dataProviderClass = DataLogin.class)
    public void loginTestFromDataProviderReadExcelCustomRow(Hashtable< String, String > data) {
        loginPage = new LoginPage();
        loginPage.login(data.get("EMAIL"), data.get("PASSWORD"));
    }

    @Test
    public void loginTestTestInvalidEmail() {
        LogUtils.info("Running test case loginTestTestInvalidEmail ");
        loginPage = new LoginPage();
        loginPage.loginInvalidEmail("admin@example123.com", "123456");

    }
}
