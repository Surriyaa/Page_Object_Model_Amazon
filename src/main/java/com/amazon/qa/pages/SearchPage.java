package com.amazon.qa.pages;

import com.amazon.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends TestBase {

    // Page Factory - Object Repository
    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    @FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
    WebElement searchResultsText;

    @FindBy(xpath = "//div[@data-cel-widget='search_result_2']3")
    WebElement firstProduct;

    @FindBy(xpath = "//div[@data-cel-widget='search_result_2']")
    WebElement firstProductLink;

    // Constructor to initialize elements
    public SearchPage() {
        PageFactory.initElements(driver, this);
    }

    // Actions/Methods

    // 1. Validate Search Page Title
    public String validateSearchPageTitle() {
        return driver.getTitle();
    }

    // 2. Check if Search Box is Displayed
    public boolean isSearchBoxDisplayed() {
        return searchBox.isDisplayed();
    }

    // 3. Search for a Product
    public void searchProduct(String productName) {
        searchBox.clear();
        searchBox.sendKeys(productName);
        searchButton.click();
    }
    public boolean validateSearchResults() {
        return driver.findElements(By.cssSelector(".s-search-results .s-result-item")).size() > 0;
    }

    // 4. Validate First Search Result
    public String getFirstSearchResult() {
        return firstProduct.getText();
    }

    // 5. Click on First Search Result
    public void clickFirstSearchResult() {
        firstProductLink.click();
    }
}

