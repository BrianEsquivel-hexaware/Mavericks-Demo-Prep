package org.example.pages;

import org.openqa.selenium.By;

public class AdminPage {

    public static final By addEmpButtonXP = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
    public static final By userRoleDropXP = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div");
    public static final By listboxDivXP = By.xpath("//div[@role='listbox']");
    public static final By statusDropXP = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div");
    public static final By EmpNameInputXP = By.xpath("//input[@placeholder='Type for hints...']");

}