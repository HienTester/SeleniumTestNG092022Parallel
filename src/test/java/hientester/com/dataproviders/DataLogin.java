package hientester.com.dataproviders;

import hientester.com.helpers.ExcelHelper;
import hientester.com.helpers.SystemsHelper;
import org.testng.annotations.DataProvider;

public class DataLogin {
    @DataProvider(name = "dataProviderLoginCRM",parallel =true)
    public Object[][] dataLoginCRM() {
        return new Object[][]{
                {"admin@example.com", "123456"}, {"admin@example.com", "123456"}, {"admin@example.com", "123456"}
        };
    }

    @DataProvider(name = "dataProviderLoginCMS")
    public Object[][] dataLoginCMS() {
        return new Object[][]{
                {"admin@example.com", "123456", 123}, {"admin@example.com", "123456", 1234}
        };
    }

    @DataProvider(name = "data_provider_login_excel")
    public Object[][] dataLoginCRMFromExcel() {
        ExcelHelper excelHelpers = new ExcelHelper();
        Object[][] data = excelHelpers.getExcelData(SystemsHelper.getCurrentDir() + "src/test/resources/datatest/CRM.xlsx", "Login");
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_custom_row")
    public Object[][] dataLoginCRMFromExcelCustomRow() {
        ExcelHelper excelHelpers = new ExcelHelper();
        Object[][] data = excelHelpers.getDataHashTable(SystemsHelper.getCurrentDir() + "src/test/resources/datatest/CRM.xlsx", "Login",4,5 );
        System.out.println("Login Data from Excel: " + data);
        return data;
    }
}
