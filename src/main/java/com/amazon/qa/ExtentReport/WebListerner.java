package com.amazon.qa.ExtentReport;

import com.amazon.qa.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class WebListerner extends TestBase implements ITestListener {
    private static final Logger log = LogManager.getLogger(WebListerner.class); // Logger instance

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Execution started...");
    }
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started");
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed");
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped");
    }
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Execution ended...");
    }
}
