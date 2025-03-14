package org.example.pages.utils;

import org.example.pages.TimesheetPage;
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

    public void selectDate() throws InterruptedException {
        driver.findElement(TimesheetPage.dateInputXP).click();
        driver.findElement(TimesheetPage.dateSelectionXP).click();
        Thread.sleep(2000);
    }

    public void editTimesheet() throws InterruptedException {
        driver.findElement(TimesheetPage.editBtnXP).click();
        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForResults.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(TimesheetPage.projectNameInputXP));
        try {
            driver.findElement(TimesheetPage.deleteBtnXP).click();
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        Thread.sleep(3000);
    }

    public boolean successfulAddTime() throws InterruptedException {
        boolean success = true;
        success = textValidator(TimesheetPage.statusSubXP);
        if(success) {
            success = textValidator(TimesheetPage.totalHoursXP);
        }
        return success;
    }

    public boolean textValidator(By XPath) {
        try {
            return driver.findElement(XPath).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void searchEmpTimesheet() throws InterruptedException {
        WebElement empInput = driver.findElement(TimesheetPage.empSearchInputXP);
        empInput.sendKeys("P");
        Thread.sleep(2000);
        empInput.sendKeys(Keys.ARROW_DOWN);
        empInput.sendKeys(Keys.ENTER);
        driver.findElement(TimesheetPage.viewTSButtonXP).click();
        Thread.sleep(2000);
    }

    public void validateTotalHours() {

    }

}
