package com.amazon.qa.pages;


import com.amazon.qa.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends TestBase {
    private static final Logger log = LogManager.getLogger(CartPage.class); // Logger instance
    // Object Repository (OR) - Using @FindBy annotations
    @FindBy(xpath = "//h2[normalize-space()='Shopping Cart']")
    WebElement shoppingCartHeader;

    @FindBy(xpath = "//span[@id='sc-subtotal-amount-buybox']")
    WebElement cartSubtotal;

    @FindBy(xpath = "//input[@value='Delete']")
    WebElement deleteItemButton;

    @FindBy(xpath = "//h2[normalize-space()='Your Amazon Cart is empty.']")
    WebElement emptyCartMessage;

    @FindBy(xpath = "//a[normalize-space()='continue shopping']")
    WebElement continueShoppingButton;

    // Constructor to initialize WebElements
    public CartPage() {
        PageFactory.initElements(driver, this);
        log.info("CartPage initialized and WebElements are ready.");
    }
    // 5 Methods
    // Validate that the Shopping Cart page is displayed
    public boolean validateCartPageHeader() {
        log.info("Validating if Shopping Cart header is displayed.");
        boolean isDisplayed = shoppingCartHeader.isDisplayed();
        log.info("Shopping Cart header displayed: {}", isDisplayed);
        return isDisplayed;
    }

    // Get the subtotal amount displayed in the cart
    public String getCartSubtotal() {
        log.info("Cart subtotal retrieved: {}", cartSubtotal.getText());
        return cartSubtotal.getText();
    }

    // Remove an item from the cart
    public boolean removeItemFromCart() {
        log.info("Attempting to remove an item from the cart.");
        if (deleteItemButton.isDisplayed()) {
            deleteItemButton.click();
            log.info("Item successfully removed from the cart.");
            return true; // Item removed successfully
        } else {
            log.warn("No item found to remove.");
            return false; // No item to remove
        }
    }

    // Check if the cart is empty
    public boolean isCartEmpty() {
        log.info("Checking if the shopping cart is empty.");
        return emptyCartMessage.isDisplayed();
    }
    public HomePage clickContinueShopping() {
        log.info("Clicking on 'Continue Shopping' button.");
        continueShoppingButton.click();
        return new HomePage(); // Navigate back to the HomePage
    }
}

