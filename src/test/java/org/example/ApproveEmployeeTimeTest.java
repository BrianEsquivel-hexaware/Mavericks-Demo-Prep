package org.example;

import org.example.pages.utils.AdminPageUtil;
import org.example.pages.utils.TimesheetPageUtil;
import org.example.utils.ReportUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApproveEmployeeTimeTest extends BaseTest {

    @Test(priority = 1)
    public void firstAdminLogin() throws IOException, InterruptedException {
        //1. Navigate to app, login as admin and moves to Admin module
        navigateToApp();
        AdminPageUtil adminPage = new AdminPageUtil(driver);
        adminPage.moveToSection(adminPage.title);
        WebElement title = adminPage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(adminPage.title));
    }

    @Test(dependsOnMethods = "firstAdminLogin")
    public void createNewUser() throws InterruptedException, IOException {
        //2. Create a new user, validate and logout
        AdminPageUtil adminPage = new AdminPageUtil(driver);
        adminPage.addNewEmployee();
        adminPage.successfulAdd();
        ReportUtils.addScreenShotSuccess(driver, test, "User successfully added");
        adminPage.logout();
    }

    @Test(dependsOnMethods = "createNewUser")
    public void userMovesToTimeModule() throws IOException, InterruptedException {
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
        //6. Login as Admin again and moves to Time module
        adminLogin();
        TimesheetPageUtil timePage = new TimesheetPageUtil(driver);
        timePage.moveToSection(timePage.title);
        WebElement title = timePage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(timePage.title));
    }

    @Test(dependsOnMethods = "secondAdminLogin")
    public void searchTimesheetByUser() throws IOException, InterruptedException {
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
        //10. Login as the new user and moves to time module
        userLogin();
        TimesheetPageUtil timePage = new TimesheetPageUtil(driver);
        timePage.moveToSection(timePage.title);
        WebElement title = timePage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(timePage.title));

        //11. Validate the total hours and the message of "Approved"
        Assert.assertTrue(timePage.userValidatesTimeApprove());
        ReportUtils.addScreenShotSuccess(driver, test, "User validated the Approved Status and the total of hours");
        timePage.logout();
        Thread.sleep(2000);
    }
}
