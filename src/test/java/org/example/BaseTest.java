package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.LoginPage;
import org.example.pages.utils.LoginPageUtil;
import org.example.utils.PropertyUtils;
import org.example.utils.ReportUtils;
import org.example.utils.ScreenShotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import static org.example.utils.ScreenShotUtils.clearFolder;

public class BaseTest {

    ExtentReports extent;
    ExtentTest test;
    WebDriver driver;

    //Used to create an Extent Reporter, add to it the needed properties and info, and to create the
    //driver that will be used among all the test case
    @BeforeTest
    public void setup(ITestContext context){
        String basePath = PropertyUtils.getProperty("reports.source");
        ExtentSparkReporter spark = new ExtentSparkReporter(basePath+"ApproveEmployeeTimeTestReport.html");
        spark.config().setReportName("Timesheet Approval Report");
        spark.config().setDocumentTitle("Test Results");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Brian Esquivel");
        extent.setSystemInfo("Java Version", "21");
        extent.setSystemInfo("Selenium Version", "4.29.0");
        extent.setSystemInfo("TestNG Version", "7.11.0");
        extent.setSystemInfo("Extent Reports Version", "5.1.2");
        clearFolder(PropertyUtils.getProperty("reportSS.source"));
        driver = new FirefoxDriver();
    }

    //Closes the driver instance and refreshes the report info
    @AfterTest
    public void teardown(){
        driver.quit();
        extent.flush();
    }

    //Generates a new test in the report for each method in the main test
    @BeforeMethod
    public void setUp(Method method){
        test = extent.createTest(method.getName());
    }

    //Helps to log the status of the just finished method and adds a SS to identify the
    //exact moment where the test failed in case of failing
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
    }

    //Helps to navigate to the site for the first time and login as an admin
    public void navigateToApp() throws InterruptedException, IOException {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(2000);
        ReportUtils.addScreenShotSuccess(driver, test, "Navigated to app");
        adminLogin();
    }

    //Logs in with the credentials of the new user
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

    //Logs in with the credentials of the admin
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
