package hientester.com.pages.customers;

import hientester.com.keywords.WebUI;
import static hientester.com.keywords.WebUI.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CustomerDetailPage  extends AddNewCustomerPage{
  //Object in Customer Detail Page
    //Không phải khai báo lại
    //Không bị duplicated
    //Tiết kiệm thời gian
    //Lỡ có thay đổi thì chỉ cần sửa 1 nơi =>AddNewCustomerPage

    public void checkCustomerDetail(String customerName){
        waitForPageLoaded();
        System.out.println(getAttributeElement(company,"value"));
        System.out.println(getAttributeElement(vat,"value"));
        System.out.println(getAttributeElement(phoneNumber,"value"));

        //Kiểm tra thì phải dùng Assert
        //Phải dùng Equals chứ không nên dùng Contains
        Assert.assertEquals(getAttributeElement(company,"value"),customerName,"Company Name not match");
        Assert.assertEquals(getAttributeElement(vat,"value"),"10","VAT Name not match");
        Assert.assertEquals(getAttributeElement(phoneNumber,"value"),"0123456789","Phone Number not match");
    }
}
