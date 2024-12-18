package com.amazon.qa.pages;

import com.amazon.qa.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {
    private static final Logger log = LogManager.getLogger(HomePage.class); // Logger instance

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
        log.info("HomePage initialized and WebElements are ready.");
    }
    public String validateHomePageTitle(){
        log.info("validating the title of the homepage");
        return driver.getTitle();
    }
    public String  validateUrl(){
        log.info("validating the URL of the homepage");
        return driver.getCurrentUrl();
    }
    public boolean validateAmazonLogo(){
        log.info("validating the Logo of the homepage");
        return amazonLogo.isDisplayed();
    }
    public CartPage validateCart(){
        cart.click();
        log.info("validating the Cart of the homepage");
        return new CartPage();
    }
    public ReturnsAndOrdersPage validateReturnAndOrders(){
        returnsAndOrders.click();
        log.info("validating the ReturnsAndOrdersPage of the homepage");
        return new ReturnsAndOrdersPage();
    }


}
