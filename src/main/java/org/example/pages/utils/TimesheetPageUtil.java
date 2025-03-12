package org.example.pages.utils;

import org.openqa.selenium.WebDriver;

public class TimesheetPageUtil extends BasePageUtil {

    private WebDriver driver;
    public final String title = "Timesheet";

    public TimesheetPageUtil(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
