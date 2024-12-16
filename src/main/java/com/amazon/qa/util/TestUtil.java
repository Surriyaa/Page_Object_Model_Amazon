package com.amazon.qa.util;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.amazon.qa.base.TestBase.driver;

public class TestUtil
{
    public static long PAGE_LOAD_TIMEOUT=10;
    public static long IMPLICIT_WAIT_TIMEOUT=10;
    static Workbook book;
    static Sheet sheet;

    public static Object[][] getTestData(String sheetName) {
        String TESTDATA_SHEET_PATH = "src/main/java/com/amazon/qa/testdata/TestData.xlsx";
        try (FileInputStream file = new FileInputStream(TESTDATA_SHEET_PATH)) {
            book = WorkbookFactory.create(file);
            sheet = book.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with name '" + sheetName + "' does not exist");
            }

            // Adjust row count to avoid accessing non-existing rows
            int rowCount = sheet.getLastRowNum(); // 0-based index for the last row
            int colCount = sheet.getRow(0).getLastCellNum(); // Number of columns in the first row
            Object[][] data = new Object[rowCount][colCount];

            for (int i = 0; i < rowCount; i++) {
                for (int k = 0; k < colCount; k++) {
                    data[i][k] = sheet.getRow(i + 1).getCell(k).toString(); // Access data safely
                }
            }
            return data;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read test data file: " + e.getMessage());
        }}
    public static String takeScreenshotAtEndOfTest(WebDriver driver,String method) throws IOException {
        // Check if the driver is not null and supports taking screenshots

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Get the current project directory
            String currentDir = System.getProperty("user.dir");

            // Ensure the "screenshots" directory exists
            File screenshotDir = new File(currentDir + "/screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();  // Create the directory if it doesn't exist
            }
            String dest=screenshotDir + "/"+method+ + System.currentTimeMillis() + ".png";
            // Copy the screenshot file to the specified location (src, dest)
            FileUtils.copyFile(scrFile, new File(dest));
            return dest;
        }
    }


