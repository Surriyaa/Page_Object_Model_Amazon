package com.amazon.qa.testcases;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.CartPage;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.LoginPage;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTest extends TestBase {
    private static final Logger log = LogManager.getLogger(CartPageTest.class);
    HomePage homePage;
    LoginPage loginPage;
    CartPage cartPage;

    public CartPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        log.info("Initializing the browser and navigating to Home Page");
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
        log.info("Logged into the application successfully.");
        cartPage = homePage.validateCart();
        log.info("Navigated to the Cart Page.");
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate the header displayed on the Cart Page")
    @Feature("Cart Page Validation")
    @Story("User verifies that the Cart Page header is visible")
    public void validateCartPageHeaderTest() {
        log.info("Starting validateCartPageHeaderTest.");
        boolean isHeaderDisplayed = cartPage.validateCartPageHeader();
        log.info("Cart Page header displayed: " + isHeaderDisplayed);
        Assert.assertTrue(isHeaderDisplayed, "Cart Page header is not displayed!");
        log.info("validateCartPageHeaderTest completed successfully.");
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate the subtotal amount displayed in the cart")
    @Feature("Cart Page Validation")
    @Story("User checks the subtotal amount for items in the cart")
    public void validateCartSubtotalTest() {
        log.info("Starting validateCartSubtotalTest.");
        String subtotal = cartPage.getCartSubtotal();
        log.info("Cart Subtotal retrieved: " + subtotal);
        Assert.assertNotNull(subtotal, "Cart subtotal is null!");
        System.out.println("Cart Subtotal: " + subtotal);
        log.info("validateCartSubtotalTest completed successfully.");
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Remove an item from the cart and verify the cart becomes empty")
    @Feature("Cart Item Removal")
    @Story("User removes items from the cart and verifies cart status")
    public void removeItemFromCartTest() {
        log.info("Starting removeItemFromCartTest.");
        boolean isItemRemoved = cartPage.removeItemFromCart();
        log.info("Item removed from cart: " + isItemRemoved);
        Assert.assertTrue(isItemRemoved, "Item could not be removed from the cart!");
        boolean isCartEmpty = cartPage.isCartEmpty();
        log.info("Cart empty status after removal: " + isCartEmpty);
        Assert.assertTrue(isCartEmpty, "Cart is not empty after removing the item!");
        log.info("removeItemFromCartTest completed successfully.");
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that the cart is empty")
    @Feature("Cart Page Validation")
    @Story("User verifies that the cart is empty")
    public void validateEmptyCartTest() {
        log.info("Starting validateEmptyCartTest.");
        boolean isCartEmpty = cartPage.isCartEmpty();
        log.info("Cart empty status: " + isCartEmpty);
        Assert.assertTrue(isCartEmpty, "Cart is not empty!");
        log.info("validateEmptyCartTest completed successfully.");
    }

    @Test(priority = 5)
    @Severity(SeverityLevel.MINOR)
    @Description("Verify the functionality of Continue Shopping button")
    @Feature("Cart Page Navigation")
    @Story("User clicks on Continue Shopping and navigates back to Home Page")
    public void continueShoppingTest() {
        log.info("Starting continueShoppingTest.");
        HomePage homePageAfterShopping = cartPage.clickContinueShopping();
        String homePageTitle = homePageAfterShopping.validateHomePageTitle();
        log.info("Home Page title after Continue Shopping: " + homePageTitle);
        Assert.assertEquals(homePageTitle, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
        log.info("continueShoppingTest completed successfully.");
    }

    @AfterMethod
    public void tearDown() {
        log.info("Closing the browser.");
        driver.quit();
        log.info("Browser closed successfully.");
    }
}
