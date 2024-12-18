package com.amazon.qa.testcases;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.CartPage;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.LoginPage;
import com.amazon.qa.pages.ReturnsAndOrdersPage;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
    private static final Logger log = LogManager.getLogger(HomePageTest.class); // Logger instance

    HomePage homePage;
    LoginPage loginPage;
    ReturnsAndOrdersPage returnsAndOrdersPage;
    CartPage cartPage;

    public HomePageTest() {
        super();
    }

    @BeforeMethod
    public void setup() {
        log.info("Initializing the browser and navigating to the login page...");
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
        log.info("Successfully logged in and navigated to the Home Page.");
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate the title of the Home Page")
    @Feature("Home Page Validation")
    @Story("User verifies that the title of the Home Page is correct")
    public void titleTest() {
        log.info("Starting titleTest to validate the Home Page title.");
        String title = homePage.validateHomePageTitle();
        log.info("Home Page Title: " + title);
        Assert.assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
        log.info("titleTest completed successfully.");
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate the URL of the Home Page")
    @Feature("Home Page Validation")
    @Story("User verifies that the URL of the Home Page is correct")
    public void urlTest() {
        log.info("Starting urlTest to validate the Home Page URL.");
        String url = homePage.validateUrl();
        log.info("Home Page URL: " + url);
        Assert.assertEquals(url, "https://www.amazon.in/?ref_=nav_custrec_signin");
        log.info("urlTest completed successfully.");
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate if the Amazon logo is displayed on the Home Page")
    @Feature("Home Page Validation")
    @Story("User verifies that the Amazon logo is visible on the Home Page")
    public void logoTest() {
        log.info("Starting logoTest to validate the Amazon logo visibility.");
        boolean flag = homePage.validateAmazonLogo();
        log.info("Amazon Logo Displayed: " + flag);
        Assert.assertTrue(flag, "Amazon logo is not displayed!");
        log.info("logoTest completed successfully.");
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate navigation to the Cart Page")
    @Feature("Home Page Navigation")
    @Story("User navigates to the Cart Page from the Home Page")
    public void cartTest() {
        log.info("Starting cartTest to validate navigation to the Cart Page.");
        cartPage = homePage.validateCart();
        log.info("Navigated to Cart Page successfully.");
        Assert.assertNotNull(cartPage, "Cart Page navigation failed!");
        log.info("cartTest completed successfully.");
    }

    @Test(priority = 5)
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate navigation to the Returns and Orders Page")
    @Feature("Home Page Navigation")
    @Story("User navigates to the Returns and Orders Page from the Home Page")
    public void returnsAndOrdersPageTest() {
        log.info("Starting returnsAndOrdersPageTest to validate Returns and Orders page navigation.");
        returnsAndOrdersPage = homePage.validateReturnAndOrders();
        log.info("Navigated to Returns and Orders Page successfully.");
        Assert.assertNotNull(returnsAndOrdersPage, "Returns and Orders Page navigation failed!");
        log.info("returnsAndOrdersPageTest completed successfully.");
    }

    @AfterMethod
    public void closing() {
        log.info("Closing the browser after test execution.");
        driver.quit();
        log.info("Browser closed successfully.");
    }
}
