package hientester.com.testcases;

import hientester.com.common.BaseTest;
import hientester.com.drivers.DriverManager;
import hientester.com.helpers.PropertiesHelper;
import hientester.com.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test
    public void loginTestSuccess1() {
        //Khởi tạo đối tượng trang LoginPage
        //Truyền driver từ BaseTest
        loginPage = new LoginPage();

        //Gọi hàm "login" từ LoginPage để dùng
        loginPage.login(PropertiesHelper.getValue("email"),"password");
    }

    @Test
    public void loginTestSuccess2() {
        //Khởi tạo đối tượng trang LoginPage
        //Truyền driver từ BaseTest
        loginPage = new LoginPage();

        //Gọi hàm "login" từ LoginPage để dùng
        loginPage.login("admin@example.com", "123456");
    }

    @Test
    public void loginTestTestInvalidEmail() {
        //Khởi tạo đối tượng trang LoginPage
        //Truyền driver từ BaseTest
        loginPage = new LoginPage();

        //Gọi hàm "login" từ LoginPage để dùng
        loginPage.loginInvalidEmail("admin@example123.com", "123456");

    }
}
