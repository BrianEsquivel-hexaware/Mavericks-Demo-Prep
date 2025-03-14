package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.utils.AdminPageUtil;
import org.example.pages.utils.TimesheetPageUtil;
import org.example.utils.PropertyUtils;
import org.example.utils.ReportUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.example.utils.ScreenShotUtils.clearFolder;

public class ApproveEmployeeTimeTest extends BaseTest {

    @BeforeTest
    public void setup(){
        String basePath = PropertyUtils.getProperty("reports.source");
        ExtentSparkReporter spark = new ExtentSparkReporter(basePath+"TimesheetApprovalReport.html");
        spark.config().setReportName("Timesheet Approval Report");
        spark.config().setDocumentTitle("Test Results");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Brian Esquivel");
        clearFolder(PropertyUtils.getProperty("reportSS.source"));
        driver = new FirefoxDriver();
    }

    @Test(priority = 1)
    public void firstAdminLogin() throws IOException, InterruptedException {
        test = extent.createTest("firstAdminLogin");

        //1. Navigate to app, login as admin and moves to Admin module
        navigateToApp();
        AdminPageUtil adminPage = new AdminPageUtil(driver);
        adminPage.moveToSection(adminPage.title);
        WebElement title = adminPage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(adminPage.title));
    }

    @Test(dependsOnMethods = "firstAdminLogin")
    public void createNewUser() throws InterruptedException, IOException {
        test = extent.createTest("createNewUser");

        //2. Create a new user, validate and logout
        AdminPageUtil adminPage = new AdminPageUtil(driver);
        adminPage.addNewEmployee();
        adminPage.successfulAdd();
        ReportUtils.addScreenShotSuccess(driver, test, "User successfully added");
        adminPage.logout();
    }

    @Test(dependsOnMethods = "createNewUser")
    public void userMovesToTimeModule() throws IOException, InterruptedException {
        test = extent.createTest("userMovesToTimeModule");

        //3. Login as the new user
        userLogin();

        //4. Move to Time module
        TimesheetPageUtil timePage = new TimesheetPageUtil(driver);
        timePage.moveToSection(timePage.title);
        WebElement title = timePage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(timePage.title));
    }

    @Test(dependsOnMethods = "userMovesToTimeModule")
    public void userCreatesTimesheet() throws IOException, InterruptedException {
        test = extent.createTest("userCreatesTimesheet");

        //5. Add a Timesheet for the new employee
        TimesheetPageUtil timePage = new TimesheetPageUtil(driver);
        timePage.selectDate();
        timePage.editTimesheet();
        Assert.assertTrue(timePage.successfulAddTime());
        ReportUtils.addScreenShotSuccess(driver, test, "Timesheet successfully added and submitted with the correct total hours");
        timePage.logout();
    }

    @Test(dependsOnMethods = "userCreatesTimesheet")
    public void secondAdminLogin() throws IOException, InterruptedException {
        test = extent.createTest("secondAdminLogin");

        //6. Login as Admin again and moves to Time module
        adminLogin();
        TimesheetPageUtil timePage = new TimesheetPageUtil(driver);
        timePage.moveToSection(timePage.title);
        WebElement title = timePage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(timePage.title));
    }

    @Test(dependsOnMethods = "secondAdminLogin")
    public void searchTimesheetByUser() throws IOException, InterruptedException {
        test = extent.createTest("searchTimesheetByUser");

        //7. Search the employee´s timesheet by name
        TimesheetPageUtil timePage = new TimesheetPageUtil(driver);
        timePage.searchEmpTimesheet();

        //8. Validate the hours in the employee´s Timesheet
        timePage.selectDate();
        Assert.assertTrue(timePage.validateTotalHours());
        ReportUtils.addScreenShotSuccess(driver, test, "New user´s Timesheet searched and has the correct total hours");
    }

    @Test(dependsOnMethods = "searchTimesheetByUser")
    public void adminApprovesTimesheet() throws IOException, InterruptedException {
        test = extent.createTest("adminApprovesTimesheet");

        //9. Approve the timesheet with the "Approved" message
        TimesheetPageUtil timePage = new TimesheetPageUtil(driver);
        timePage.approveTimesheet();
        Assert.assertTrue(timePage.successfulApprove());
        ReportUtils.addScreenShotSuccess(driver, test, "User successfully approved");
        Thread.sleep(1000);
        timePage.logout();
    }

    @Test(dependsOnMethods = "adminApprovesTimesheet")
    public void userValidatesApproval() throws IOException, InterruptedException {
        test = extent.createTest("userValidatesApproval");

        //10. Login as the new user and moves to time module
        userLogin();
        TimesheetPageUtil timePage = new TimesheetPageUtil(driver);
        timePage.moveToSection(timePage.title);
        WebElement title = timePage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(timePage.title));

        //11. Validate the total hours and the message of "Approved"

    }

}
