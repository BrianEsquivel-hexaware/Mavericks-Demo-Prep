package org.example.pages;

import org.openqa.selenium.WebDriver;

public class AdminPage extends BasePage {

    private WebDriver driver;
    public final String title = "Admin";

    public AdminPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}