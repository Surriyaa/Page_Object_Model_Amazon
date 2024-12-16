package com.amazon.qa.ExtentReport;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.util.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ExtentReportManager extends TestBase implements ITestListener {
    ExtentSparkReporter sparkReporter;//for updating the UI of report
    ExtentReports extentReports;
    ExtentTest test;


    public void onStart(ITestContext context) {
        sparkReporter=new ExtentSparkReporter("C:\\Users\\ASUS\\IdeaProjects\\Amazon\\reports\\ExtentReport.html");
        sparkReporter.config().setDocumentTitle("Amazon Extent Report");
        sparkReporter.config().setReportName("Amazon Test Case Report- 1");
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports=new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    public void onTestSuccess(ITestResult result) {
        test=extentReports.createTest(result.getName());//create a new entry in report
        test.log(Status.PASS,"Test case is PASSED "+result.getName());
    }

    public void onTestFailure(ITestResult result) {
        String base64Screenshot;
        test=extentReports.createTest(result.getName());//create a new entry in report
        try {
            base64Screenshot = TestUtil.takeScreenshotAtEndOfTest(driver, result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.log(Status.FAIL,"Test case is FAILED "+result.getName());
        test.log(Status.FAIL,"Test case FAILED cause is "+result.getThrowable());
        test.log(Status.FAIL,"Test FAILURE Screenshot "+ test.addScreenCaptureFromBase64String(base64Screenshot));

    }

    public void onTestSkipped(ITestResult result) {
        test=extentReports.createTest(result.getName());//create a new entry in report
        test.log(Status.SKIP,"Test case is SKIPPED "+result.getName());    }

    public void onFinish(ITestContext context) {
        extentReports.flush();    }
}
