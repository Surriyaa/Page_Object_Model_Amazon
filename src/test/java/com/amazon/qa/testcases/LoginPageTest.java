package com.amazon.qa.testcases;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.LoginPage;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Amazon Login Functionality Tests")
@Feature("Login Page Validations")
public class LoginPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    private static final Logger log = LogManager.getLogger(LoginPageTest.class); // Logger instance

    public LoginPageTest() {
        super();
    }

    @BeforeMethod
    @Step("Initializing the browser and navigating to the Login Page")
    public void setup() {
        log.info("Initializing the browser and navigating to the Login Page...");
        initialization();
        loginPage = new LoginPage();
        log.info("Login Page initialized successfully.");
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the Login Page title is correct")
    @Story("User validates the title of the Login Page")
    public void titleTest() {
        log.info("Starting titleTest to validate the Login Page title.");
        String title = loginPage.validateTitle();
        log.info("Login Page Title: " + title);
        Assert.assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
        log.info("titleTest completed successfully.");
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify the Amazon logo is displayed on the Login Page")
    @Story("User checks if the Amazon logo is visible on the Login Page")
    public void logoTest() {
        log.info("Starting logoTest to validate the Amazon logo on the Login Page.");
        boolean flag = loginPage.validateAmazonLogo();
        log.info("Amazon Logo Displayed: " + flag);
        Assert.assertTrue(flag, "Amazon logo is not displayed on the Login Page!");
        log.info("logoTest completed successfully.");
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that the user is able to log in successfully")
    @Story("User logs into the application and navigates to the Home Page")
    public void loginTest() {
        log.info("Starting loginTest to validate user login functionality.");
        homePage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
        log.info("Login successful, navigated to Home Page.");
        Assert.assertNotNull(homePage, "Home Page object is null after login!");
        log.info("loginTest completed successfully.");
    }

    @AfterMethod
    @Step("Closing the browser after test execution")
    public void tearDown() {
        log.info("Closing the browser after test execution.");
        driver.quit();
        log.info("Browser closed successfully.");
    }
}
