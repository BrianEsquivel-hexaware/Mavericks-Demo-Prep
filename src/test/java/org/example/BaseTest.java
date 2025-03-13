package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.example.pages.AdminPage;
import org.example.pages.LoginPage;
import org.example.pages.utils.LoginPageUtil;
import org.example.utils.PropertyUtils;
import org.example.utils.ReportUtils;
import org.example.utils.ScreenShotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;

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
        adminLogin();
    }

    public void userLogin() throws InterruptedException, IOException {
        LoginPageUtil loginPage = new LoginPageUtil(driver);
        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForResults.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(LoginPage.userInputXP));
        loginPage.LoginKeys(PropertyUtils.getProperty("user.name"), PropertyUtils.getProperty("user.pass"));
        ReportUtils.addScreenShotSuccess(driver, test, "Typed new user credentials");
        loginPage.submitLogin();
        Thread.sleep(3000);
        ReportUtils.addScreenShotSuccess(driver, test, "New user login successful");
    }

    public void adminLogin() throws InterruptedException, IOException {
        LoginPageUtil loginPage = new LoginPageUtil(driver);
        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForResults.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(LoginPage.userInputXP));
        loginPage.LoginKeys(PropertyUtils.getProperty("admin.name"), PropertyUtils.getProperty("admin.pass"));
        ReportUtils.addScreenShotSuccess(driver, test, "Typed admin credentials");
        loginPage.submitLogin();
        Thread.sleep(3000);
        ReportUtils.addScreenShotSuccess(driver, test, "Admin login successful");
    }

}
