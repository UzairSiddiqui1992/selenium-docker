package com.vinsguru.tests.flightreservation;
import com.vinsguru.tests.AbstractTest;
import com.vinsguru.pages.flightreservation.*;
import com.vinsguru.tests.flightreservation.model.FlightReservationTestData;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import com.vinsguru.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataFilePath")
    public void setParams(String testDataFilePath){
        this.testData= JsonUtil.getTestData(testDataFilePath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterFirstName(testData.firstname());
        registrationPage.enterLastName(testData.lastname());
        registrationPage.enterEmail(testData.email());
        registrationPage.enterPassword(testData.password());
        registrationPage.enterStreet(testData.street());
        registrationPage.enterCity(testData.city());
        registrationPage.selectState(testData.state());
        registrationPage.enterZip(testData.zip());
        registrationPage.clickRegister();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getConfirmationFirstName(),testData.firstname());
        registrationConfirmationPage.clicksFlightSearchBtn();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectNumberOfPassengers(testData.noOfPassengers());
        flightSearchPage.clicksOnSearchFlightsBtn();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void selectsFlightTest(){
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlight();
        flightSelectionPage.clickConfirmFlight();
    }

    @Test(dependsOnMethods = "selectsFlightTest")
    public void flightConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(),testData.expectedPrice());
    }
}
