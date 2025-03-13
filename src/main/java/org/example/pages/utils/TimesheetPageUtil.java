package org.example.pages.utils;

import org.example.pages.AdminPage;
import org.example.pages.TimesheetPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        driver.findElement(TimesheetPage.editBtnXP).click();
        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForResults.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(TimesheetPage.projectNameInputXP));
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
    }

}
