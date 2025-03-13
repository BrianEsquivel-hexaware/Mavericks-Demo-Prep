package org.example.pages;

import org.openqa.selenium.By;

public class TimesheetPage{

    public static final By dateInputXP = By.xpath("//input[@placeholder='yyyy-dd-mm']");
    public static final By dateSelectionXP = By.xpath("//div[contains(text(),'12')]");
    public static final By editBtnXP = By.xpath("//button[normalize-space()='Edit']");
    public static final By projectNameInputXP = By.xpath("//input[@placeholder='Type for hints...']");
    public static final By activitySelectXP = By.xpath("//div[@class='oxd-select-text--after']");
    public static final By genDivXP = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]");
    public static final By specificActDivXP = By.xpath("//div[@role='listbox']");
    public static final By specificActSelXP = By.xpath("//*[normalize-space()='QA Testing']");
    public static final By mondayInputXP = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[3]/div/div[2]/input");
    public static final By tuesdayInputXP = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[4]/div/div[2]/input");
    public static final By wednesdayInputXP = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[5]/div/div[2]/input");
    public static final By thursdayInputXP = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[6]/div/div[2]/input");
    public static final By fridayInputXP = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[7]/div/div[2]/input");
    public static final By saturdayInputXP = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[8]/div/div[2]/input");
    public static final By sundayInputXP = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[9]/div/div[2]/input");

}
