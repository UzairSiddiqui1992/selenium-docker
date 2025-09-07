package com.vinsguru.pages.flightreservation;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(css = "p.mt-3 b")
    private WebElement confirmationFirstName;

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightSearchBtn;

    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt(){
    this.wait.until(ExpectedConditions.visibilityOf(goToFlightSearchBtn));
    return goToFlightSearchBtn.isDisplayed();
    }

    public String getConfirmationFirstName(){
        this.wait.until(ExpectedConditions.visibilityOf(confirmationFirstName));
        return confirmationFirstName.getText();
    }
    public void clicksFlightSearchBtn(){
        goToFlightSearchBtn.click();
    }

}
