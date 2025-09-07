package com.vinsguru.pages.vendorPortal;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DashboardPage extends AbstractPage {
    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginElement;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(id = "dataTable_info")
    private WebElement searchResultCountElementInfo;

    @FindBy(css = "#dataTable_filter input")
    private WebElement searchTextBoxElement;
    
    @FindBy(css = "#userDropdown img")
    private WebElement userProfileImageElement;

    @FindBy(css = "a[data-target='#logoutModal']")
    private WebElement logoutLink;

    @FindBy(linkText = "Logout")
    private WebElement logoutPopButtonElement;


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
    this.wait.until(ExpectedConditions.visibilityOf(monthlyEarningElement));
    return monthlyEarningElement.isDisplayed();
    }

    public String getMonthlyEarnings(){
        return monthlyEarningElement.getText();
    }

    public String getYearlyEarnings(){
        return annualEarningElement.getText();
    }
    public String getProfitMargins(){
        return profitMarginElement.getText();
    }
    public String getAvailableInventory(){
        return availableInventoryElement.getText();
    }
    public void searchOrderHistoryBy(String keyword){
        this.searchTextBoxElement.sendKeys(keyword);
    }
    public int getSearchResultsCount(){
        String resultsText = this.searchResultCountElementInfo.getText();
        String[] arr = resultsText.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Results count: {}",count);
        return count;
    }

    public void logout(){
        userProfileImageElement.click();
        logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(logoutPopButtonElement));
        logoutPopButtonElement.click();
    }
}
