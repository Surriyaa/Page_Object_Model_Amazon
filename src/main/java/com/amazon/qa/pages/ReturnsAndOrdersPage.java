package com.amazon.qa.pages;

import com.amazon.qa.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReturnsAndOrdersPage extends TestBase {
    private static final Logger log = LogManager.getLogger(ReturnsAndOrdersPage.class); // Logger instance
    @FindBy(id = "nav-orders")
    WebElement returnAndOrdersBtn;

    @FindBy(xpath = "//h1[normalize-space()='Your Orders']")
    WebElement yourOrders;

    @FindBy(xpath = "//a[@class='a-link-normal'][normalize-space()='Buy Again']")
    WebElement buyAgainBtn;

    @FindBy(xpath = "//a[normalize-space()='Cancelled Orders']")
    WebElement cancelledOrdersBtn;

    @FindBy(id = "searchOrdersInput")
    WebElement searchBox;

    @FindBy(xpath = "//input[@class='a-button-input']")
    WebElement searchBtn;

    public ReturnsAndOrdersPage(){
        PageFactory.initElements(driver,this);
        log.info("ReturnsAndOrdersPage initialized and WebElements are ready.");
    }
    // Actions and methods
    public ReturnsAndOrdersPage returnAndOrdersValidation(){
        returnAndOrdersBtn.click();
        log.info("ReturnsAndOrdersPage page is now available on screen");
        return new ReturnsAndOrdersPage();
    }

    public boolean yourOrdersValidation(){
        log.info("Your Orders  visible status"+yourOrders.isDisplayed());
        return yourOrders.isDisplayed();
    }

    public BuyAgainPage buyAgainValidation(){
        buyAgainBtn.click();
        log.info("buyAgainBtn button is clickable.");
        return new BuyAgainPage();
    }

    public CancelledOrdersPage cancelledOrdersValidation(){
        cancelledOrdersBtn.click();
        log.info("cancelledOrdersBtn is clickable ");
        return new CancelledOrdersPage();
    }

    public ReturnsAndOrdersSearchPage searchPageValidation(){
        searchBox.sendKeys("ASIAN");
        log.info("Input provided in search box");
        searchBox.click();
        log.info("Search box is working");
        return new ReturnsAndOrdersSearchPage();
    }
}
