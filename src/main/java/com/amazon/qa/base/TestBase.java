package com.amazon.qa.base;

import com.amazon.qa.util.TestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    private static final Logger log = LogManager.getLogger(TestBase.class); // Logger instance

    // Constructor: Loads configuration properties
    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("C:\\Users\\ASUS\\IdeaProjects\\Amazon\\src\\main\\java\\com\\amazon\\qa\\config\\config.properties");
            prop.load(fis);
            log.info("Properties file loaded successfully.");
        } catch (IOException e) {
            log.error("Failed to load config.properties file", e);
            throw new RuntimeException("Configuration file loading failed.");
        }
    }

    // Browser and environment initialization
    public void initialization() {
        String browser = prop.getProperty("browser");
        log.info("Initializing browser: {}", browser);

        try {
            // Browser selection
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
                log.info("ChromeDriver initialized.");
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
                log.info("FirefoxDriver initialized.");
            } else {
                log.error("Unsupported browser: " + browser);
                throw new RuntimeException("Browser not supported: " + browser);
            }

            // Apply browser settings
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
            log.info("Browser window maximized and timeouts set.");

            // Load cookies and navigate to URL
            driver.get(prop.getProperty("url"));
            log.info("Navigated to URL: {}", prop.getProperty("url"));
            loadCookies();
            driver.navigate().refresh();
            log.info("Cookies applied and page refreshed.");

        } catch (Exception e) {
            log.error("Exception during browser initialization", e);
            throw new RuntimeException("Browser initialization failed.", e);
        }
    }

    // Method to load cookies from a JSON file
    private void loadCookies() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray cookiesArray = (JSONArray) parser.parse(new FileReader("C:\\Users\\ASUS\\Documents\\cookies1.json"));

            for (Object obj : cookiesArray) {
                JSONObject cookieJson = (JSONObject) obj;
                Cookie cookie = new Cookie(
                        (String) cookieJson.get("name"),
                        (String) cookieJson.get("value"),
                        (String) cookieJson.get("domain"),
                        (String) cookieJson.get("path"),
                        null, // Expiry date
                        Boolean.parseBoolean(cookieJson.get("secure").toString()),
                        Boolean.parseBoolean(cookieJson.get("httpOnly").toString())
                );
                driver.manage().addCookie(cookie);
            }
        } catch (Exception e) {
            log.error("Error while loading cookies from JSON file.", e);
        }
    }
}
