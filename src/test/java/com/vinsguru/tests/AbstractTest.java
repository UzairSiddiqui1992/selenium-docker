package com.vinsguru.tests;
import com.vinsguru.listener.TestListener;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Listeners({TestListener.class})
public abstract class AbstractTest {
    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    public ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("autofill.profile_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        return options;
    }

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }


    @BeforeTest
    public void setUp(ITestContext context) throws MalformedURLException {

        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        driver.manage().window().maximize();
        context.setAttribute(Constants.DRIVER, this.driver);
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities;
        if (Constants.CHROME.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
            capabilities = getChromeOptions();
        } else if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
            capabilities = new FirefoxOptions();
        } else if (Constants.EDGE.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
            capabilities = new EdgeOptions();
        } else {
            throw new RuntimeException("Browser not supported");
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String remoteUrl = String.format(urlFormat, hubHost);
        log.info("Remote url: {}", remoteUrl);
        return new RemoteWebDriver(new URL(remoteUrl), capabilities);
    }

    private WebDriver getLocalDriver() {
        String browser = Config.get(Constants.BROWSER);
        if ("chrome".equalsIgnoreCase(browser)) {
            return new ChromeDriver(getChromeOptions());
        } else if ("firefox".equalsIgnoreCase(browser)) {
            return new FirefoxDriver();
        } else if ("edge".equalsIgnoreCase(browser)) {
            return new EdgeDriver();
        } else {
            throw new RuntimeException("Browser not supported or not specified");
        }
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
