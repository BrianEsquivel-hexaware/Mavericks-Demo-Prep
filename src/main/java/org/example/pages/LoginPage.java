package org.example.pages;

import org.example.utils.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    private WebDriver driver;

    public static final By userInputXP = By.xpath("//input[@placeholder='Username']");
    public static final By passwordInputXP = By.xpath("//input[@placeholder='Password']");

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void login(){
        String username = PropertyUtils.getProperty("user.name");
        String password = PropertyUtils.getProperty("user.pass");
        WebElement userInput = driver.findElement(userInputXP);
        userInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(passwordInputXP);
        passwordInput.sendKeys(password);
        WebElement submitButton = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
        submitButton.click();
    }
}