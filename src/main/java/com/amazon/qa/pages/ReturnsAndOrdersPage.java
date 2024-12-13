package com.amazon.qa.pages;

import com.amazon.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReturnsAndOrdersPage extends TestBase {

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
    }

    // Actions and methods

    public ReturnsAndOrdersPage returnAndOrdersValidation(){
        returnAndOrdersBtn.click();
        return new ReturnsAndOrdersPage();
    }

    public boolean yourOrdersValidation(){
        return yourOrders.isDisplayed();
    }

    public BuyAgainPage buyAgainValidation(){
        buyAgainBtn.click();
        return new BuyAgainPage();
    }

    public CancelledOrdersPage cancelledOrdersValidation(){
        cancelledOrdersBtn.click();
        return new CancelledOrdersPage();
    }

    public ReturnsAndOrdersSearchPage searchPageValidation(){
        searchBox.sendKeys("ASIAN");
        searchBox.click();
        return new ReturnsAndOrdersSearchPage();
    }



}
