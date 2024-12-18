package com.amazon.qa.testcases;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.SearchPage;
import com.amazon.qa.util.TestUtil;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchPageTest extends TestBase {
    private static final Logger log = LogManager.getLogger(SearchPageTest.class);
    SearchPage searchPage;
    String sheetName = "searchData";

    public SearchPageTest() {
        super();
    }

    @BeforeMethod
    public void setup() {
        log.info("Initializing the browser and navigating to the search page...");
        initialization();
        searchPage = new SearchPage();
        log.info("Setup completed successfully.");
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Validates the title of the search page")
    @Feature("Search Page Title Validation")
    @Story("User navigates to the search page and validates the title")
    public void validateSearchPageTitleTest() {
        log.info("Starting validateSearchPageTitleTest...");
        String title = searchPage.validateSearchPageTitle();
        log.info("Search Page Title: " + title);
        Assert.assertTrue(title.contains("Amazon"), "Search Page title is incorrect.");
        log.info("validateSearchPageTitleTest completed successfully.");
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checks if the search box is displayed on the page")
    @Feature("Search Box Display Validation")
    @Story("User validates the presence of the search box")
    public void isSearchBoxDisplayedTest() {
        log.info("Starting isSearchBoxDisplayedTest...");
        boolean isDisplayed = searchPage.isSearchBoxDisplayed();
        log.info("Is Search Box Displayed: " + isDisplayed);
        Assert.assertTrue(isDisplayed, "Search box is not displayed.");
        log.info("isSearchBoxDisplayedTest completed successfully.");
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Tests the search functionality with a specific product name")
    @Feature("Product Search Validation")
    @Story("User searches for a product and verifies the results")
    public void searchProductTest() {
        log.info("Starting searchProductTest...");
        String productName = "Laptop";
        log.info("Searching for product: " + productName);
        searchPage.searchProduct(productName);
        String resultsText = searchPage.getFirstSearchResult();
        log.info("First Search Result: " + resultsText);
        Assert.assertTrue(resultsText.contains("Laptop"), "Search results do not contain the expected product.");
        log.info("searchProductTest completed successfully.");
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.NORMAL)
    @Description("Validates the first product result in search results")
    @Feature("Search Results Validation")
    @Story("User validates the first search result for a product")
    public void validateFirstSearchResultTest() {
        log.info("Starting validateFirstSearchResultTest...");
        String productName = "Headphones";
        log.info("Searching for product: " + productName);
        searchPage.searchProduct(productName);
        String firstProduct = searchPage.getFirstSearchResult();
        log.info("First Product in Search Results: " + firstProduct);
        Assert.assertNotNull(firstProduct, "First product in search results is not displayed.");
        log.info("validateFirstSearchResultTest completed successfully.");
    }

    @Test(priority = 5)
    @Severity(SeverityLevel.NORMAL)
    @Description("Tests navigation to the product details page by clicking the first result")
    @Feature("Product Details Navigation")
    @Story("User clicks on the first search result and verifies navigation")
    public void clickFirstSearchResultTest() {
        log.info("Starting clickFirstSearchResultTest...");
        String productName = "Books";
        log.info("Searching for product: " + productName);
        searchPage.searchProduct(productName);
        searchPage.clickFirstSearchResult();
        String newPageTitle = searchPage.validateSearchPageTitle();
        log.info("New Page Title after clicking: " + newPageTitle);
        Assert.assertTrue(newPageTitle.contains("Books"), "Failed to navigate to the product details page.");
        log.info("clickFirstSearchResultTest completed successfully.");
    }

    @DataProvider(name = "provideTestData")
    public Object[][] provideTestData() {
        log.info("Fetching test data from Excel sheet: " + sheetName);
        Object[][] data = TestUtil.getTestData(sheetName);
        log.info("Data fetched successfully. Number of rows: " + data.length);
        return data;
    }

    @Test(dataProvider = "provideTestData", priority = 6)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Tests search functionality with multiple product names from test data")
    @Feature("Parameterized Product Search")
    @Story("User searches for multiple products using a data provider")
    public void testSearchProduct(String productName) {
        log.info("Starting testSearchProduct for product: " + productName);
        searchPage.searchProduct(productName);
        boolean isResultsDisplayed = searchPage.validateSearchResults();
        log.info("Validation Status for Search Results: " + isResultsDisplayed);
        Assert.assertTrue(isResultsDisplayed, "No search results displayed for: " + productName);
        log.info("testSearchProduct completed successfully for product: " + productName);
    }

    @AfterMethod
    public void tearDown() {
        log.info("Closing the browser after test execution...");
        driver.quit();
        log.info("Browser closed successfully.");
    }
}
