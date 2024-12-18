package com.amazon.qa.testcases;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.*;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Amazon Returns and Orders Functionality Tests")
@Feature("Returns and Orders Page Validations")
public class ReturnsAndOrdersTest extends TestBase {

    private static final Logger log = LogManager.getLogger(ReturnsAndOrdersTest.class); // Logger instance

    HomePage homePage;
    LoginPage loginPage;
    ReturnsAndOrdersPage returnsAndOrdersPage;
    BuyAgainPage buyAgainPage;
    ReturnsAndOrdersSearchPage returnsAndOrdersSearchPage;
    CancelledOrdersPage cancelledOrdersPage;

    public ReturnsAndOrdersTest() {
        super();
    }

    @BeforeMethod
    @Step("Initializing the browser and logging in to navigate to Returns and Orders Page")
    public void setUp() {
        log.info("Initializing the browser and navigating to the login page...");
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
        log.info("Login successful, navigating to the Returns and Orders page.");
        returnsAndOrdersPage = homePage.validateReturnAndOrders();
        log.info("Successfully navigated to the Returns and Orders page.");
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate the title of the Returns and Orders Page")
    @Story("User checks the Returns and Orders page title")
    public void returnAndOrdersTitleTest() {
        log.info("Starting returnAndOrdersTitleTest...");
        returnsAndOrdersPage = returnsAndOrdersPage.returnAndOrdersValidation();
        String title = driver.getTitle();
        log.info("Returns and Orders Page Title: " + title);
        Assert.assertEquals(title, "Your Orders", "Title did not match!");
        log.info("returnAndOrdersTitleTest completed successfully.");
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate 'Your Orders' section visibility")
    @Story("User verifies 'Your Orders' section is displayed")
    public void yourOrdersTest() {
        log.info("Starting yourOrdersTest...");
        boolean isValid = returnsAndOrdersPage.yourOrdersValidation();
        log.info("Your Orders Validation Status: " + isValid);
        Assert.assertTrue(isValid, "Your Orders section validation failed!");
        log.info("yourOrdersTest completed successfully.");
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate navigation to 'Buy Again' page and verify its title")
    @Story("User navigates to 'Buy Again' page and verifies the title")
    public void buyAgainTitleTest() {
        log.info("Starting buyAgainTitleTest...");
        buyAgainPage = returnsAndOrdersPage.buyAgainValidation();
        String title = driver.getTitle();
        log.info("Buy Again Page Title: " + title);
        Assert.assertEquals(title, "Buy Again", "Title did not match!");
        log.info("buyAgainTitleTest completed successfully.");
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate URL of Cancelled Orders page after navigation")
    @Story("User navigates to Cancelled Orders page and verifies the URL")
    public void cancelledOrdersUrlTest() {
        log.info("Starting cancelledOrdersUrlTest...");
        cancelledOrdersPage = returnsAndOrdersPage.cancelledOrdersValidation();
        String url = driver.getCurrentUrl();
        log.info("Cancelled Orders Page URL: " + url);
        Assert.assertEquals(url, "https://www.amazon.in/gp/legacy/order-history?orderFilter=cancelled&ref_=ppx_yo2ov_dt_b_tb_cancel",
                "URL did not match!");
        log.info("cancelledOrdersUrlTest completed successfully.");
    }

    @Test(priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate navigation to Returns and Orders Search Page")
    @Story("User navigates to Returns and Orders Search Page")
    public void searchPageTest() {
        log.info("Starting searchPageTest...");
        returnsAndOrdersSearchPage = returnsAndOrdersPage.searchPageValidation();
        log.info("Search Page validation result: " + (returnsAndOrdersSearchPage != null));
        Assert.assertNotNull(returnsAndOrdersSearchPage, "The Page landing is wrong");
        log.info("searchPageTest completed successfully.");
    }

    @AfterMethod
    @Step("Closing the browser after test execution")
    public void tearDown() {
        log.info("Closing the browser after test execution...");
        driver.quit();
        log.info("Browser closed successfully.");
    }
}
