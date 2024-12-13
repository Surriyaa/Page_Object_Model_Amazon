package com.amazon.qa.testcases;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReturnsAndOrdersTest extends TestBase {

    HomePage homePage;
    LoginPage loginPage;
    ReturnsAndOrdersPage returnsAndOrdersPage;
    BuyAgainPage buyAgainPage;
    ReturnsAndOrdersSearchPage returnsAndOrdersSearchPage;
    CancelledOrdersPage cancelledOrdersPage;

   public ReturnsAndOrdersTest(){
       super();
   }

   @BeforeMethod
   public void setUp() {
       // Initialize the driver and navigate to the homepage
       initialization();
       loginPage = new LoginPage();
       homePage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
       returnsAndOrdersPage=homePage.validateReturnAndOrders() ; // Navigate to the Returns & Orders Page
   }

   @Test(priority = 1)
       public void returnAndOrdersTitleTest(){
           returnsAndOrdersPage=returnsAndOrdersPage.returnAndOrdersValidation();
            String title=driver.getTitle();
           Assert.assertEquals(title,"Your Orders");
       }

       @Test(priority = 2)
       public void yourOrdersTest(){
       Assert.assertTrue(returnsAndOrdersPage.yourOrdersValidation());
       }

       @Test(priority = 3)
       public void buyAgainTitleTest(){
       buyAgainPage= returnsAndOrdersPage.buyAgainValidation();
       String title=driver.getTitle();
       Assert.assertEquals(title,"Buy Again","Incorrect Title");
       }

       @Test(priority = 4)
       public void cancelledOrdersUrlTest(){
           cancelledOrdersPage= returnsAndOrdersPage.cancelledOrdersValidation();
           String url=driver.getCurrentUrl();
           Assert.assertEquals(url,"https://www.amazon.in/gp/legacy/order-history?orderFilter=cancelled&ref_=ppx_yo2ov_dt_b_tb_cancel","Incorrect URL Matched");
       }

       @Test(priority = 5)
       public  void searchPageTest(){
       returnsAndOrdersSearchPage = returnsAndOrdersPage.searchPageValidation();
       Assert.assertNotNull(returnsAndOrdersSearchPage,"The Page landing is wrong");
       }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
