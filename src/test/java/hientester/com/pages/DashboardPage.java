package hientester.com.pages;

import hientester.com.keywords.WebUI;
import static hientester.com.keywords.WebUI.*;
import hientester.com.pages.customers.CustomersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage {
        //Data trong nội bộ trang Dashboard
        private String PAGE_URl = "https://crm.anhtester.com/admin/";
        private String PAGE_TEXT = "Dashboard Options";

        //Các Object
        By menuCustomer = By.xpath("//span[normalize-space()='Customers']");
        By buttonOptionDashboard = By.xpath("//div[@class='screen-options-btn']");
        By widgetStatistics = By.xpath("//div[@id='widget-top_stats']");
        By checkboxQuickStatistics = By.xpath("//label[normalize-space()='Quick Statistics']");


        public void verifyDashboardpage(){
            //Kiểm tra URL chứa phần tử thuộc trang DB
            //Kiểm tra Text hoặc Object chỉ có trong DB có
            Assert.assertEquals(getCurrentURL(),PAGE_URl,"URL chưa đúng trang Dashboard");
            Assert.assertTrue(checkElementExist(buttonOptionDashboard), "Dashboard Options not existing.");
        }
        //Hàm xử lý
        public CustomersPage openCustomerPage(){
            waitForPageLoaded();
            //driver.findElement(menuCustomer).click();
            clickElement(menuCustomer);

            return new CustomersPage();
        }

        public void clickCheckboxQuickStatistics() {
            //driver.findElement(buttonOptionDashboard).click();
            clickElement(buttonOptionDashboard);

            //waitForElementVisible(checkboxQuickStatistics,5);

            //driver.findElement(checkboxQuickStatistics).click();
            clickElement(checkboxQuickStatistics);
        }

        public void verifyFilterStatistic(){
            //Kiểm tra widget này đang hiển thị (visible)
            Assert.assertTrue(verifyElementVisible(widgetStatistics,5),"The Widget Statistics default does not visible.");
            //Nhấn uncheck this widget
            clickCheckboxQuickStatistics();
            //Kiểm tra widget trên đã bị ẩn ( not visible)
            Assert.assertTrue(verifyElementNotVisible(widgetStatistics,5),"The Widget Statistics is visible.");
        }
}
