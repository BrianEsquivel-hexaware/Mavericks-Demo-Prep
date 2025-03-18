package org.example.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ReportUtils {

    //Helps to add any screenshot to the report whenever in the code is called
    public static void addScreenShotSuccess(WebDriver driver, ExtentTest test, String message) throws IOException {
        String ssPath = ScreenShotUtils.getScreenShotPath(driver);
        test.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(ssPath).build());
    }

    public static void addScreenShotFail(WebDriver driver, ExtentTest test, String message) throws IOException {
        String ssPath = ScreenShotUtils.getScreenShotPath(driver);
        test.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(ssPath).build());
    }
}
