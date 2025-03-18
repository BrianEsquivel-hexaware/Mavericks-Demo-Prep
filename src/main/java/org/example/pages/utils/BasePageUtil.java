package org.example.pages.utils;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePageUtil extends BasePage {

    private WebDriver _driver;

    public BasePageUtil(WebDriver driver) {
        _driver = driver;
    }

    //Helps to validate that the navigation to a specific module was successful
    public WebElement getSectionTitle() {
        return _driver.findElement(sectionTitleXP);
    }

    //Makes the driver to logout from the site
    public void logout(){
        _driver.findElement(dropDownMenuXP).click();
        _driver.findElement(logoutButtonXP).click();
    }

    //Helps to navigate among the different sections in the site
    public void moveToSection(String sectionTitle) throws InterruptedException {
        _driver.findElement(By.xpath("//aside[@class='oxd-sidepanel']//li//a//span[text()='"+sectionTitle+"']")).click();
        Thread.sleep(2000);
    }

}
