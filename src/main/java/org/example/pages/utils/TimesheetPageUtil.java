package org.example.pages.utils;

import org.example.pages.TimesheetPage;
import org.example.utils.PropertyUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TimesheetPageUtil extends BasePageUtil {

    private WebDriver driver;
    public final String title = "Time";

    public TimesheetPageUtil(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //Selects the date in the timesheet calendar to choose a specific week
    public void selectDate() throws InterruptedException {
        driver.findElement(TimesheetPage.dateInputXP).click();
        driver.findElement(TimesheetPage.dateSelectionXP).click();
        Thread.sleep(2000);
    }

    //Makes the process to edit the timesheet and submit it
    public void editTimesheet() throws InterruptedException {
        driver.findElement(TimesheetPage.editBtnXP).click();
        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForResults.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(TimesheetPage.projectNameInputXP));
        try {
            driver.findElement(TimesheetPage.deleteBtnXP).click();
        } catch (Exception e) {
            System.out.println("The Timesheet was new so there was no need to delete any past one");
        }
        Thread.sleep(1000);
        WebElement actualInput = driver.findElement(TimesheetPage.projectNameInputXP);
        actualInput.sendKeys("B");
        Thread.sleep(2000);
        actualInput.sendKeys(Keys.ARROW_DOWN);
        actualInput.sendKeys(Keys.ENTER);
        driver.findElement(TimesheetPage.activitySelectXP).click();
        Thread.sleep(1000);
        actualInput = driver.findElement(TimesheetPage.genDivXP);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", actualInput);
        actualInput = driver.findElement(TimesheetPage.genDivXP);
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", actualInput);
        driver.findElement(TimesheetPage.specificActDivXP).findElement(TimesheetPage.specificActSelXP).click();
        driver.findElement(TimesheetPage.mondayInputXP).sendKeys("8");
        driver.findElement(TimesheetPage.tuesdayInputXP).sendKeys("8");
        driver.findElement(TimesheetPage.wednesdayInputXP).sendKeys("8");
        driver.findElement(TimesheetPage.thursdayInputXP).sendKeys("8");
        driver.findElement(TimesheetPage.fridayInputXP).sendKeys("8");
        driver.findElement(TimesheetPage.saturdayInputXP).sendKeys("0");
        driver.findElement(TimesheetPage.sundayInputXP).sendKeys("0");
        driver.findElement(TimesheetPage.saveBtnXP).click();
        waitForResults.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(TimesheetPage.editBtnXP));
        Thread.sleep(2000);
        try {
            driver.findElement(TimesheetPage.submitBtnXP).click();
        } catch (Exception e) {
            System.out.println("Timesheet was already submitted, it only was edited");
        }
        Thread.sleep(3000);
    }

    //Validates that the timesheet has the status of "submitted" and that the total hours are correct
    public boolean successfulAddTime() throws InterruptedException {
        boolean success = true;
        success = textValidator(TimesheetPage.statusSubXP);
        if(success) {
            success = textValidator(TimesheetPage.totalHoursXP);
        }
        return success;
    }

    //Used to make the validations needed to check the timesheet status and total hours
    public boolean textValidator(By XPath) {
        try {
            return driver.findElement(XPath).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //Searches for a specific timesheet by the new user´s name
    public void searchEmpTimesheet() throws InterruptedException {
        WebElement empInput = driver.findElement(TimesheetPage.empSearchInputXP);
        empInput.sendKeys(PropertyUtils.getProperty("emp.name"));
        Thread.sleep(2000);
        empInput.sendKeys(Keys.ARROW_DOWN);
        empInput.sendKeys(Keys.ENTER);
        driver.findElement(TimesheetPage.viewTSButtonXP).click();
        Thread.sleep(2000);
    }

    //Used to assert the total hours validation
    public boolean validateTotalHours() {
        boolean success = true;
        success = textValidator(TimesheetPage.adminViewHoursXP);
        return success;
    }

    //Used to approve the total hours for a timesheet as an admin
    public void approveTimesheet(){
        WebElement actualElement = driver.findElement(TimesheetPage.commentsTextareaXP);
        actualElement.sendKeys("Approved");
        actualElement = driver.findElement(TimesheetPage.generalHTMLXP);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", actualElement);
        driver.findElement(TimesheetPage.approveBtnXP).click();
    }

    //Validates that the "Successfully Approved" message appears
    public boolean successfulApprove() throws InterruptedException {
        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForResults.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(TimesheetPage.approveMsgXP));
        Thread.sleep(500);
        return textValidator(TimesheetPage.statusApprovedXP);
    }

    //Used to validate from the user´s side that their timesheet appears with the status of approved
    //and with the correct total hours
    public boolean userValidatesTimeApprove(){
        boolean success = true;
        success = textValidator(TimesheetPage.statusApprovedXP);
        if(success) {
            success = textValidator(TimesheetPage.adminViewHoursXP);
        }
        return success;
    }

}
