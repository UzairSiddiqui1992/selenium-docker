package com.vinsguru.tests.vendorPortal;
import com.vinsguru.pages.vendorPortal.DashboardPage;
import com.vinsguru.pages.vendorPortal.LoginPage;
import com.vinsguru.tests.AbstractTest;
import com.vinsguru.tests.vendorPortal.model.VendorPortalTestData;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import com.vinsguru.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataFilePath")
    public void setPageObjects(String testDataFilePath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataFilePath,VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        loginPage.isAt();
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        dashboardPage.isAt();
        Assert.assertEquals(dashboardPage.getMonthlyEarnings(),testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getYearlyEarnings(),testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargins(),testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),testData.availableInventory());

        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(),testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logout(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
