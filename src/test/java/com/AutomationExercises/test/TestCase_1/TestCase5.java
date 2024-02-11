package com.AutomationExercises.test.TestCase_1;

import com.AutomationExercises.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCase5 {
    /*
    1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Signup / Login' button
5. Verify 'New User Signup!' is visible
6. Enter name and already registered email address
7. Click 'Signup' button
8. Verify error 'Email Address already exist!' is visible
     */
    WebDriver driver;
    WebDriverWait wait;

    //1. Launch browser
    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait=new WebDriverWait(driver,20);
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void testCase5() throws InterruptedException {
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");

        //3. Verify that home page is visible successfully
        System.out.println("driver.getCurrentUrl() = " + driver.getCurrentUrl());
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://automationexercise.com/";
        Assert.assertEquals(actualUrl,expectedUrl);

        //4. Click on 'Signup / Login' button
        WebElement loginButton = driver.findElement(By.partialLinkText("Signup / Login"));
        loginButton.click();
        Thread.sleep(2000);

        //5. Verify 'New User Signup!' is visible
        WebElement newUserSignUpBtn = driver.findElement(By.xpath("//*[text()='New User Signup!']"));
        System.out.println(newUserSignUpBtn.getText());
        Assert.assertTrue(newUserSignUpBtn.isDisplayed());

        //6. Enter name and already registered email address
        WebElement name = driver.findElement(By.xpath("//input[@placeholder='Name']"));
        name.sendKeys("HAsAN");
        WebElement email = driver.findElement(By.xpath("(//input[@placeholder='Email Address'])[2]"));
        email.sendKeys("Hasan@gmail.com");
        //7. Click 'Signup' button
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).submit();
        //8. Verify error 'Email Address already exist!' is visible
        WebElement alreadyExistText = driver.findElement(By.xpath("//*[text()='Email Address already exist!']"));
        System.out.println(alreadyExistText.getText());
        Assert.assertTrue(alreadyExistText.isDisplayed());
    }
}
