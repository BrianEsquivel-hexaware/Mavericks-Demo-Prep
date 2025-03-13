package org.example.pages.utils;

import org.example.pages.BasePage;
import org.example.pages.LoginPage;
import org.example.utils.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageUtil extends BasePageUtil {

    private WebDriver driver;

    public LoginPageUtil(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void LoginKeys(String username, String password) {
        WebElement userInput = driver.findElement(LoginPage.userInputXP);
        userInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(LoginPage.passwordInputXP);
        passwordInput.sendKeys(password);
    }

    public void submitLogin(){
        WebElement submitButton = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
        submitButton.click();
    }
}
