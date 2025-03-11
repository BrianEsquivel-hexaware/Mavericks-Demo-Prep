package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage extends BasePage {

    private WebDriver driver;
    public final String title = "Admin";

    public static final By addEmpButtonXP = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
    public static final By userRoleDropXP = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div");
    public static final By userRolesDivXP = By.xpath("//div[@role='listbox']");

    public AdminPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public By xpathBySpecificRole(String role){
        return By.xpath("//*[normalize-space()='"+role+"']");
    }

    public void addNewEmployee() throws InterruptedException {
        driver.findElement(addEmpButtonXP).click();
        Thread.sleep(2000);
        driver.findElement(userRoleDropXP).click();
        driver.findElement(userRolesDivXP).findElement(xpathBySpecificRole("ESS")).click();
        Thread.sleep(2000);
    }
}