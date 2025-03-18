package org.example.pages;

import org.openqa.selenium.By;

public class BasePage {

    //XPaths used for locating elements in the BasePage for base methods
    public static final By sectionTitleXP = By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']");
    public static final By dropDownMenuXP = By.xpath("//li[@class='oxd-userdropdown']");
    public static final By logoutButtonXP = By.xpath("//a[normalize-space()='Logout']");

}
