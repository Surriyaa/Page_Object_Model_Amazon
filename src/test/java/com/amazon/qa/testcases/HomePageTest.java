package com.amazon.qa.testcases;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.CartPage;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.LoginPage;
import com.amazon.qa.pages.ReturnsAndOrdersPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
    HomePage homePage;
    LoginPage loginPage;
    ReturnsAndOrdersPage returnsAndOrdersPage;
    CartPage cartPage;
    public HomePageTest(){
        super();
    }

    @BeforeMethod
    public void setup(){
        initialization();
        loginPage=new LoginPage();
        homePage=loginPage.signIn((prop.getProperty("username")),prop.getProperty("password"));

    }

    //Test cases should be independent with each other
    //Before each TC launch browser and login
    //After each testcase close the browser

    @Test(priority = 1)
    public void titleTest(){
        String title= homePage.validateHomePageTitle();
        Assert.assertEquals(title,"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
    }
    @Test(priority = 2)
    public void urlTest(){
        String url=homePage.validateUrl();
        Assert.assertEquals(url,"https://www.amazon.in/?ref_=nav_custrec_signin");
    }
    @Test(priority = 3)
    public void logoTest(){
        boolean flag=homePage.validateAmazonLogo();
        Assert.assertTrue(flag);
    }
    @Test(priority = 4)
    public void cartTest(){
        cartPage=homePage.validateCart();
    }
    @Test(priority = 5)
    public  void returnsAndOrdersPageTest(){
        returnsAndOrdersPage=homePage.validateReturnAndOrders();
    }

    @AfterMethod
    public void closing(){
        driver.quit();
    }
}
