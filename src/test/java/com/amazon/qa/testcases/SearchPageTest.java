package com.amazon.qa.testcases;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.SearchPage;
import com.amazon.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class SearchPageTest extends TestBase {

    SearchPage searchPage;
    String sheetName = "searchData";

    public SearchPageTest() {
        super();
    }

    @BeforeMethod
    public void setup() {
        initialization();
        searchPage = new SearchPage();
    }

    // 1. Test to validate Search Page Title
    @Test(priority = 1)
    public void validateSearchPageTitleTest() {
        String title = searchPage.validateSearchPageTitle();
        Assert.assertTrue(title.contains("Amazon"), "Search Page title is incorrect.");
    }

    // 2. Test to check if Search Box is displayed
    @Test(priority = 2)
    public void isSearchBoxDisplayedTest() {
        Assert.assertTrue(searchPage.isSearchBoxDisplayed(), "Search box is not displayed.");
    }

    // 3. Test to perform a product search
    @Test(priority = 3)
    public void searchProductTest() {
        searchPage.searchProduct("Laptop");
        String resultsText = searchPage.getFirstSearchResult();
        Assert.assertTrue(resultsText.contains("Laptop"), "Search results do not contain the expected product.");
    }

    // 4. Test to validate the first search result
    @Test(priority = 4)
    public void validateFirstSearchResultTest() {
        searchPage.searchProduct("Headphones");
        String firstProduct = searchPage.getFirstSearchResult();
        Assert.assertNotNull(firstProduct, "First product in search results is not displayed.");
    }

    // 5. Test to click on the first search result
    @Test(priority = 5)
    public void clickFirstSearchResultTest() {
        searchPage.searchProduct("Books");
        searchPage.clickFirstSearchResult();
        String newPageTitle = searchPage.validateSearchPageTitle();
        Assert.assertTrue(newPageTitle.contains("Books"), "Failed to navigate to the product details page.");
    }
    @DataProvider(name = "provideTestData")
    public Object[][] provideTestData() {
        // Fetch test data dynamically from the Excel sheet
        String sheetName = "searchData";
        Object[][] data = TestUtil.getTestData(sheetName); // Fetch all data from the sheet
        return data; // Return the data to the test method
    }

    @Test(dataProvider = "provideTestData",priority = 6)
    public void testSearchProduct(String productName) {
        // Step 1: Search for a product
        System.out.println("Searching for: " + productName);
        searchPage.searchProduct(productName);

        // Step 2: Validate search results are displayed
        boolean isResultsDisplayed = searchPage.validateSearchResults();
        Assert.assertTrue(isResultsDisplayed, "No search results displayed for: " + productName);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

