package org.example.pages.utils;

import org.example.pages.AdminPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        Thread.sleep(2000);
    }
}
