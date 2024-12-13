package com.amazon.qa.pages;

import com.amazon.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {
    // PageFactory - OR (Object Repository)
    @FindBy(xpath = "//div[@id='nav-signin-tooltip']")
    WebElement signInBtn;
    @FindBy(name = "email")
    WebElement username;
    @FindBy(name = "password")
    WebElement password;
    @FindBy(id = "signInSubmit")
    WebElement loginBtn;
    @FindBy(id = "createAccountSubmit")
    WebElement createAccBtn;
    @FindBy(id="nav-logo-sprites")
    WebElement amazonLogo;
    @FindBy(id = "continue")
    WebElement continueBtn;
    static LoginPage loginPage;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }
    public static String validateTitle(){
        return driver.getTitle();
    }
    public boolean validateAmazonLogo(){
        return amazonLogo.isDisplayed();
    }

    public HomePage signIn(String un,String pw){
//        try {
        signInBtn.click();
        username.sendKeys(un);
        continueBtn.click();
        password.sendKeys(pw);
        loginBtn.click();
//            throw new RuntimeException("Invalid credentials, login failed.");

            return new HomePage();

//    } catch (Exception e) {
//        // This will trigger the onException method in the EventListener
//        throw e; // Rethrow the exception to be handled by the WebDriverEventListener
//    }
    }


}
