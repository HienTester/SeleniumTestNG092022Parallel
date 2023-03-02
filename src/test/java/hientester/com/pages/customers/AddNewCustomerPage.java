package hientester.com.pages.customers;

import hientester.com.keywords.WebUI;
import static hientester.com.keywords.WebUI.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class AddNewCustomerPage {
    private String PAGE_URL = "https://crm.anhtester.com/admin/clients/client";
    private String PAGE_TEXT = "Customer Details";

    public By tabCustomerDetail = By.xpath("//a[@aria-controls='contact_info']");
    public By company = By.xpath("//input[@id='company']");
    public By vat = By.xpath("//input[@id='vat']");
    public By phoneNumber = By.xpath("//input[@id='phonenumber']");
    public By website = By.xpath("//input[@id='website']");
    public By dropdownGroups = By.xpath("//label[@for='groups_in[]']/following-sibling::div");
    public By inputGroups = By.xpath("//label[@for='groups_in[]']/following-sibling::div//input[@type='search']");
    public By currency = By.xpath("//button[@data-id='default_currency']");
    public By language = By.xpath("//button[@data-id='default_language']");
    public By address = By.xpath("//textarea[@id='address']");
    public By city = By.xpath("//input[@id='city']");
    public By state = By.xpath("//input[@id='state']");
    public By zipcode = By.xpath("//input[@id='zip']");
    public By dropdownCountry = By.xpath("//label[@for='country']/following-sibling::div");
    public By inputCountry = By.xpath("//label[@for='country']/following-sibling::div//input[@type='search']");
    public By buttonSave = By.xpath("//div[@id='profile-save-section']//button[normalize-space()='Save']");


    public void selectGroups(String groupName){
        //driver.findElement(dropdownGroups).click();
        clickElement(dropdownGroups);

        getWebElement(inputGroups).sendKeys("Gold", Keys.ENTER);

        //driver.findElement(dropdownGroups).click();
        clickElement(dropdownGroups);
    }

    public void AddDataNewCustomer(String CUSTOMER_NAME) {
        waitForPageLoaded();

        setText(company,CUSTOMER_NAME);
        setText(vat,"10");
        setText(phoneNumber,"0123456789");
        setText(website,"https://anhtester.com");
        selectGroups("Gold");
        setText(address,"Vietnam");
        setText(city,"Can Tho");
        setText(state,"Can Tho");
        setText(zipcode,"92000");
        clickElement(dropdownCountry);
        getWebElement(inputCountry).sendKeys("Vietnam", Keys.ENTER);
        clickElement(buttonSave);

        waitForPageLoaded();
    }
}
