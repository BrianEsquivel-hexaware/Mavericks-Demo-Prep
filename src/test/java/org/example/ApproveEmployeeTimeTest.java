package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.AdminPage;
import org.example.pages.utils.AdminPageUtil;
import org.example.pages.utils.TimesheetPageUtil;
import org.example.utils.PropertyUtils;
import org.example.utils.ReportUtils;
import org.openqa.selenium.WebElement;
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
    }

    @Test
    public void EmployeeTimesheet_Approved() throws IOException, InterruptedException {
        //1. Navigate to app and login
        test = extent.createTest("EmployeeTimesheet_Approved");
        navigateToApp();

        //2. Create a new user and validate
        AdminPageUtil adminPage = new AdminPageUtil(driver);
        adminPage.moveToSection(adminPage.title);
        WebElement title = adminPage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(adminPage.title));
        adminPage.addNewEmployee();
        adminPage.successfulAdd();
        ReportUtils.addScreenShotSuccess(driver, test, "User successfully added");
        adminPage.logout();

        //3. Login as the new user
        userLogin();

        //4. Move to time module
        TimesheetPageUtil timePage = new TimesheetPageUtil(driver);
        timePage.moveToSection(timePage.title);
        title = timePage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(timePage.title));

        //5. Add a Timesheet for the new employee (New changes)
        timePage.selectDate();

        //6. Login as an Admin


        //7. Go to Time module and search the employee´s name


        //8. Validate the hours in the employee´s Timesheet


        //9. Approve the timesheet with the "Approved" message


        //10. Login as the new user


        //11. Validate the total hours and the message of "Approved"

        timePage.logout();
        Thread.sleep(2000);

    }

}
