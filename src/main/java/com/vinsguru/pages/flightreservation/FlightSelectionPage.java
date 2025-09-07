package com.vinsguru.pages.flightreservation;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {

    @FindBy(name="departure-flight")
    private List<WebElement> departureFlightOptionList;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightOptionList;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightBtn;

    public FlightSelectionPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(confirmFlightBtn));
        return confirmFlightBtn.isDisplayed();
    }

    public void selectFlight(){
        int random = ThreadLocalRandom.current().nextInt(0,departureFlightOptionList.size());
        departureFlightOptionList.get(random).click();
        arrivalFlightOptionList.get(random).click();
    }

    public void clickConfirmFlight(){
        confirmFlightBtn.click();
    }
}
