package com.amazon.qa.testcases;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    LoginPageTest(){
        super();
    }
    @BeforeMethod
    public void setup(){
        initialization();
        loginPage=new LoginPage();
    }

    @Test(priority = 1)
    public void titleTest(){
        String title=LoginPage.validateTitle();
        Assert.assertEquals(title,"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
    }

    @Test(priority = 2)
    public void logoTest(){
        boolean flag=loginPage.validateAmazonLogo();
        Assert.assertTrue(flag);
    }
    @Test(priority = 3)
    public void loginTest(){
        homePage=loginPage.signIn((prop.getProperty("username")),prop.getProperty("password"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
