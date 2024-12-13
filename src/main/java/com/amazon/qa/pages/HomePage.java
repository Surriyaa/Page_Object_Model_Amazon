package com.amazon.qa.pages;

import com.amazon.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

    @FindBy(xpath = "//a[normalize-space()='Customer Service']")
    WebElement customerServive;

    @FindBy(id = "nav-link-accountList")
    WebElement accounts;

    @FindBy(id = "nav-orders")
    WebElement returnsAndOrders;

    @FindBy(id = "nav-cart")
    WebElement cart;

    @FindBy(xpath = "//a[normalize-space()='Buy Again']")
    WebElement buyAgain;

    @FindBy(id="nav-logo-sprites")
    WebElement amazonLogo;

    public HomePage(){
        PageFactory.initElements(driver,this);
    }
    public String validateHomePageTitle(){
        return driver.getTitle();
    }
    public String  validateUrl(){
        return driver.getCurrentUrl();

    }
    public boolean validateAmazonLogo(){
        return amazonLogo.isDisplayed();
    }
    public CartPage validateCart(){
        cart.click();
        return new CartPage();
    }
    public ReturnsAndOrdersPage validateReturnAndOrders(){
        returnsAndOrders.click();
        return new ReturnsAndOrdersPage();
    }


}
