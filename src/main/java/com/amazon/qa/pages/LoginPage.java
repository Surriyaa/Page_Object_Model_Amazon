package com.amazon.qa.pages;

import com.amazon.qa.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {
    private static final Logger log = LogManager.getLogger(LoginPage.class); // Logger instance

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
        log.info("LoginPage initialized and WebElements are ready.");
    }
    public String validateTitle(){
        log.info("validating the title of the LoginPage");
        return driver.getTitle();
    }
    public boolean validateAmazonLogo(){
        log.info("validating the Logo of Amazon");
        return amazonLogo.isDisplayed();
    }

    public HomePage signIn(String un,String pw){
        log.info("Attempting to signin");
        signInBtn.click();
        username.sendKeys(un);
        continueBtn.click();
        password.sendKeys(pw);
        loginBtn.click();
        log.info("SignIn Successful");
            return new HomePage();
    }
}
