package org.example.pages;

import org.openqa.selenium.WebDriver;

public class TimesheetPage extends BasePage{

    private WebDriver driver;
    public final String title = "Timesheet";

    public TimesheetPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
