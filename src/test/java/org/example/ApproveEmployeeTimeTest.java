package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.AdminPage;
import org.example.pages.utils.AdminPageUtil;
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
        test = extent.createTest("EmployeeTimesheet_Approved");
        navigateToApp();
        AdminPageUtil adminPage = new AdminPageUtil(driver);
        adminPage.moveToSection(adminPage.title);
        WebElement title = adminPage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(adminPage.title));
        adminPage.addNewEmployee();
        adminPage.successfulAdd();
        ReportUtils.addScreenShotSuccess(driver, test, "User successfully added");

        adminPage.logout();
        Thread.sleep(2000);
    }

}
