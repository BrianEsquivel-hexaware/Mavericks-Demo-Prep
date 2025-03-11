package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;
import org.example.pages.LoginPage;
import org.example.utils.ReportUtils;
import org.example.utils.ScreenShotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseTest {

    ExtentReports extent;
    ExtentTest test;
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new FirefoxDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS, "Test successfully passed");
        } else if (result.getStatus() == ITestResult.FAILURE){
            String ssPath = ScreenShotUtils.getScreenShotPath(driver);
            test.fail("Test failed in: " + result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(ssPath).build());
        } else if (result.getStatus() == ITestResult.SKIP){
            test.log(Status.SKIP, "Skipped test");
        }
        driver.quit();
    }

    @AfterTest
    public void teardown(){
        extent.flush();
    }

    public void navigateToApp() throws InterruptedException, IOException {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(2000);
        ReportUtils.addScreenShotSuccess(driver, test, "Navigated to app");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginKeys();
        ReportUtils.addScreenShotSuccess(driver, test, "Typed credentials");
        loginPage.submitLogin();
        Thread.sleep(3000);
        ReportUtils.addScreenShotSuccess(driver, test, "Login successful");
    }

}
