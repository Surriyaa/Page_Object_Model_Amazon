package com.amazon.qa.pages;

import com.amazon.qa.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends TestBase {
    private static final Logger log = LogManager.getLogger(SearchPage.class); // Logger instance

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
        log.info("SearchPage initialized and WebElements are ready.");
    }

    // Actions/Methods

    // 1. Validate Search Page Title
    public String validateSearchPageTitle() {
        log.info("SearchPAge title shown "+driver.getTitle());
        return driver.getTitle();
    }

    // 2. Check if Search Box is Displayed
    public boolean isSearchBoxDisplayed() {
        boolean displayed = searchBox.isDisplayed();
        log.info("SearchBox display status "+ displayed);
        return displayed;
    }

    // 3. Search for a Product
    public void searchProduct(String productName) {
        searchBox.clear();
        searchBox.sendKeys(productName);
        log.info("Searching a product with name: "+productName);
        searchButton.click();
        log.info("product search done");
    }
    public boolean validateSearchResults() {
        log.info("validateing SearchResults are showing or not");
        return driver.findElements(By.cssSelector(".s-search-results .s-result-item")).size() > 0;
    }

    // 4. Validate First Search Result
    public String getFirstSearchResult() {
        String firstProductName = firstProduct.getText();
        log.info("getting the firstProduct name "+ firstProductName);
        return firstProductName;
    }

    // 5. Click on First Search Result
    public void clickFirstSearchResult() {
        log.info("clicking on the first product link");
        firstProductLink.click();
    }
}

