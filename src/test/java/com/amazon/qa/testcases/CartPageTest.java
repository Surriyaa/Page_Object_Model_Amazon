package com.amazon.qa.testcases;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.CartPage;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTest extends TestBase {
    HomePage homePage;
    LoginPage loginPage;
    CartPage cartPage;

    public CartPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        // Initialize the driver and navigate to the homepage
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
        cartPage = homePage.validateCart(); // Navigate to the CartPage
    }

    // Test case to validate the CartPage header
    @Test(priority = 1)
    public void validateCartPageHeaderTest() {
        boolean isHeaderDisplayed = cartPage.validateCartPageHeader();
        Assert.assertTrue(isHeaderDisplayed, "Cart Page header is not displayed!");
    }

    // Test case to validate the cart subtotal
    @Test(priority = 2)
    public void validateCartSubtotalTest() {
        String subtotal = cartPage.getCartSubtotal();
        Assert.assertNotNull(subtotal, "Cart subtotal is null!");
        System.out.println("Cart Subtotal: " + subtotal);
    }

    // Test case to remove an item from the cart
    @Test(priority = 3)
    public void removeItemFromCartTest() {
        boolean isItemRemoved = cartPage.removeItemFromCart();
        Assert.assertTrue(isItemRemoved, "Item could not be removed from the cart!");
        boolean isCartEmpty = cartPage.isCartEmpty();
        Assert.assertTrue(isCartEmpty, "Cart is not empty after removing the item!");
    }

    // Test case to validate the empty cart message
    @Test(priority = 4)
    public void validateEmptyCartTest() {
        boolean isCartEmpty = cartPage.isCartEmpty();
        Assert.assertTrue(isCartEmpty, "Cart is not empty!");
    }

    // Test case to validate the Continue Shopping button functionality
    @Test(priority = 5)
    public void continueShoppingTest() {
        HomePage homePageAfterShopping = cartPage.clickContinueShopping();
        String homePageTitle = homePageAfterShopping.validateHomePageTitle();
        Assert.assertEquals(homePageTitle, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
        driver.quit();
    }
}

