package org.example.pages.utils;

import org.example.pages.AdminPage;
import org.example.utils.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPageUtil extends BasePageUtil{

    WebDriver driver;
    public final String title = "Admin";

    public AdminPageUtil(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //Helps to obtain specific XPaths for elements with an specific text
    public By xpathBySpecificWord(String role){
        return By.xpath("//*[normalize-space()='"+role+"']");
    }

    //Makes the complete process to add a new employee
    public void addNewEmployee() throws InterruptedException {
        driver.findElement(AdminPage.addEmpButtonXP).click();
        Thread.sleep(2000);
        driver.findElement(AdminPage.userRoleDropXP).click();
        driver.findElement(AdminPage.listboxDivXP).findElement(xpathBySpecificWord("ESS")).click();
        driver.findElement(AdminPage.statusDropXP).click();
        driver.findElement(AdminPage.listboxDivXP).findElement(xpathBySpecificWord("Enabled")).click();
        WebElement actualInput = driver.findElement(AdminPage.EmpNameInputXP);
        actualInput.sendKeys(PropertyUtils.getProperty("emp.name"));
        Thread.sleep(2000);
        actualInput.sendKeys(Keys.ARROW_DOWN);
        actualInput.sendKeys(Keys.ENTER);
        actualInput = driver.findElement(AdminPage.usernameInputXP);
        actualInput.sendKeys(PropertyUtils.getProperty("user.name"));
        actualInput = driver.findElement(AdminPage.passwordInputXP);
        actualInput.sendKeys(PropertyUtils.getProperty("user.pass"));
        actualInput = driver.findElement(AdminPage.confPassInputXP);
        actualInput.sendKeys(PropertyUtils.getProperty("user.pass"));
        driver.findElement(AdminPage.saveBtnXP).click();
    }

    //Validates that the "Successfully Added" message is shown
    public void successfulAdd() throws InterruptedException {
        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForResults.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(AdminPage.successMsgXP));
        Thread.sleep(500);
    }
}
