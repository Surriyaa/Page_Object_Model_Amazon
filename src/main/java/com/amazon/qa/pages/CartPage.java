package com.amazon.qa.pages;


import com.amazon.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends TestBase {

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
    }

    // 5 Methods

    // Validate that the Shopping Cart page is displayed
    public boolean validateCartPageHeader() {
        return shoppingCartHeader.isDisplayed();
    }

    // Get the subtotal amount displayed in the cart
    public String getCartSubtotal() {
        return cartSubtotal.getText();
    }

    // Remove an item from the cart
    public boolean removeItemFromCart() {
        if (deleteItemButton.isDisplayed()) {
            deleteItemButton.click();
            return true; // Item removed successfully
        } else {
            return false; // No item to remove
        }
    }

    // Check if the cart is empty
    public boolean isCartEmpty() {
        return emptyCartMessage.isDisplayed();
    }
    public HomePage clickContinueShopping() {
        continueShoppingButton.click();
        return new HomePage(); // Navigate back to the HomePage
    }
}

