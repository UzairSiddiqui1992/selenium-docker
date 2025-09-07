package com.vinsguru.pages.vendorPortal;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(loginButton));
        return this.loginButton.isDisplayed();
    }

    public void login(String userName, String password){
        this.usernameInput.sendKeys(userName);
        this.passwordInput.sendKeys(password);
        this.loginButton.click();
    }

    public void goTo(String url){
        this.driver.get(url);
    }
}
