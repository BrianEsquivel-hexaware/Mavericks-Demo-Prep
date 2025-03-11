package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BaseTest {

    ExtentReports extent;
    ExtentTest test;
    WebDriver _driver;

    public void navigateToApp() throws InterruptedException, IOException {
        _driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(_driver);
        loginPage.login();
        Thread.sleep(3000);
    }

}
