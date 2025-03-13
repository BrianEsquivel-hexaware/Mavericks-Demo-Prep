package org.example.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ScreenShotUtils {

    public static String getScreenShotPath(WebDriver driver) throws IOException {
        Random rand = new Random();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File(PropertyUtils.getProperty("reportSS.source")+"/"+ rand.toString()+".png");
        FileUtils.copyFile(srcFile, destFile);
        return destFile.getAbsolutePath();
    }

    public static void clearFolder(String path){
        File folder = new File(path);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if(files != null){
                for(File file : files){
                    file.delete();
                }
            }
        }
    }
}
