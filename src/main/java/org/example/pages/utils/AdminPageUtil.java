package org.example.pages.utils;

import org.example.pages.AdminPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPageUtil extends BasePageUtil{

    WebDriver driver;
    public final String title = "Admin";

    public AdminPageUtil(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public By xpathBySpecificWord(String role){
        return By.xpath("//*[normalize-space()='"+role+"']");
    }

    public void addNewEmployee() throws InterruptedException {
        driver.findElement(AdminPage.addEmpButtonXP).click();
        Thread.sleep(2000);
        driver.findElement(AdminPage.userRoleDropXP).click();
        driver.findElement(AdminPage.listboxDivXP).findElement(xpathBySpecificWord("ESS")).click();
        driver.findElement(AdminPage.statusDropXP).click();
        driver.findElement(AdminPage.listboxDivXP).findElement(xpathBySpecificWord("Enabled")).click();
        WebElement employeeInput = driver.findElement(AdminPage.EmpNameInputXP);
        employeeInput.sendKeys("Peter");
        Thread.sleep(2000);
        employeeInput.sendKeys(Keys.ARROW_DOWN);
        employeeInput.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
    }
}
