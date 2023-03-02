package hientester.com.testcases;

import hientester.com.common.BaseTest;
import hientester.com.pages.customers.CustomersPage;
import hientester.com.pages.DashboardPage;
import hientester.com.pages.LoginPage;
import org.testng.annotations.Test;

public class DashbroadTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomersPage customersPage;

    @Test
    public void testOpenMenuOnDashboard() {
        //Kiểm tra trang đăng nhập Login
        loginPage = new LoginPage();
        //Liên kết trang được xảy ra nhờ hàm login trả về là sự khởi tạo của trang Dashboard
        dashboardPage = loginPage.login("admin@example.com", "123456");

        //Kiểm tra trang Dashboard là đúng
        dashboardPage.verifyDashboardpage();

        //Kiểm tra menu Customers click vào mở được trang
        customersPage = dashboardPage.openCustomerPage();
        customersPage.verifyCustomerPage();
    }

    @Test
    public void testFilterWidgetsOnDashboard() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("admin@example.com", "123456");
        dashboardPage.verifyDashboardpage();
        //Check Filter Widgets on Dashboard
        dashboardPage.verifyFilterStatistic();

    }

}
