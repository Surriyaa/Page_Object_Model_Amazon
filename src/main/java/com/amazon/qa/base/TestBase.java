package com.amazon.qa.base;

import com.amazon.qa.util.WebEventListener;
import com.amazon.qa.util.TestUtil;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static WebDriver driver;
    public static Properties prop;
    public static EventFiringDecorator<WebDriver> e_driver;
    public static WebEventListener eventListener;

    // Constructor to load the config properties
    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("C:\\Users\\ASUS\\IdeaProjects\\FreeCRM\\src\\main\\java\\com\\amazon\\qa\\config\\config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace(); // It's good to log exceptions here.
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    // Initialization method to configure WebDriver and set up the environment
    public void initialization() {
        String browser = prop.getProperty("browser");

        // Choose the browser for WebDriver initialization
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();

            // Open the Amazon website and apply cookies from the JSON file
            driver.get("https://www.amazon.in");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Load cookies from the JSON file
            try {
                JSONParser parser = new JSONParser();
                JSONArray cookiesArray = (JSONArray) parser.parse(new FileReader("\"C:\\Users\\ASUS\\Documents\\cookies.json\""));

                for (Object obj : cookiesArray) {
                    JSONObject cookieJson = (JSONObject) obj;
                    Cookie cookie = new Cookie(
                            (String) cookieJson.get("name"),
                            (String) cookieJson.get("value"),
                            (String) cookieJson.get("domain"),
                            (String) cookieJson.get("path"),
                            null,
                            Boolean.parseBoolean(cookieJson.get("secure").toString()),
                            Boolean.parseBoolean(cookieJson.get("httpOnly").toString())
                    );
                    driver.manage().addCookie(cookie);
                }
                // Refresh the page to apply cookies
                driver.navigate().refresh();

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Now you can perform actions without CAPTCHA interruptions
            driver.get("https://www.amazon.in/gp/cart/view.html");

        } else if (browser.equals("firefox")) {
            driver=new FirefoxDriver();
        }

        // Initialize the event listener and register it with the EventFiringDecorator
        eventListener = new WebEventListener();
        e_driver = new EventFiringDecorator<>(eventListener);
        driver = e_driver.decorate(driver);

        // Browser window settings and timeouts
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);

        // Open the target URL from the config
        driver.get(prop.getProperty("url"));
    }
}
