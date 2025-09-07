package com.vinsguru.pages.flightreservation;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengerDropdown;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsBtn;

    public FlightSearchPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(searchFlightsBtn));
        return searchFlightsBtn.isDisplayed();
    }

    public void selectNumberOfPassengers(String noOfPassengers){
        Select noOfPassenger = new Select(passengerDropdown);
        noOfPassenger.selectByValue(noOfPassengers);
    }

    public void clicksOnSearchFlightsBtn(){
       this.searchFlightsBtn.click();
    }
}
