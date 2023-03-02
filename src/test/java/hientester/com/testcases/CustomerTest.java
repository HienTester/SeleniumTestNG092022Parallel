package hientester.com.testcases;

import hientester.com.common.BaseTest;
import hientester.com.pages.customers.AddNewCustomerPage;
import hientester.com.pages.customers.CustomerDetailPage;
import hientester.com.pages.customers.CustomersPage;
import hientester.com.pages.DashboardPage;
import hientester.com.pages.LoginPage;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomersPage customersPage;
    AddNewCustomerPage addNewCustomerPage;
    CustomerDetailPage customerDetailPage;

  @Test
    public void testAddNewCustomer(){
      loginPage = new LoginPage();
      dashboardPage = loginPage.login("admin@example.com","123456");
      customersPage = dashboardPage.openCustomerPage();

      customersPage.verifyCustomerPage();
      addNewCustomerPage = customersPage.clickNewCustomerButton();

      //Addison, Joseph, Adorno, Theodor, Keiko, Bailey, Philip James
      String CUSTOMER_NAME="Addison";
      addNewCustomerPage.AddDataNewCustomer(CUSTOMER_NAME);

      //Mở lại trang Customer
     dashboardPage.openCustomerPage();

     //Search giá trị Customer vừa add
      customersPage.searchCustomer(CUSTOMER_NAME);
      //Click vào giá trị Customer Name dòng đầu tiên
      customerDetailPage = customersPage.clickOnFirstRowCustomerName();
      //Check Customer Detail
      customerDetailPage.checkCustomerDetail(CUSTOMER_NAME);


  }
}
