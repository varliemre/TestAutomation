package com.AutomationExercises.test.TestCase_1;

import com.AutomationExercises.utilities.WebDriverFactory;
import com.github.javafaker.Faker;
import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCase3 {

    /*
            Test Case 3: Login User with incorrect email and password
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Signup / Login' button
            5. Verify 'Login to your account' is visible
            6. Enter incorrect email address and password
            7. Click 'login' button
            8. Verify error 'Your email or password is incorrect!' is visible
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
    public void tc02() throws InterruptedException {

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

        //5. Verify 'Login to your account' is visible
        WebElement loginText = driver.findElement(By.xpath("//*[text()='Login to your account']"));
        Assert.assertTrue(loginText.isDisplayed());

        //6. Enter incorrect email address and password
        Faker faker = new Faker();

        Actions actions=new Actions(driver);
        WebElement email = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        actions.click(email)
                .sendKeys(faker.internet().emailAddress())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().password())
                //7. Click 'login' button
                .sendKeys(Keys.ENTER)
                .perform();

        //8. Verify error 'Your email or password is incorrect!' is visible
        WebElement incorrectText = driver.findElement(By.xpath("//p[text()='Your email or password is incorrect!']"));
        Assert.assertTrue(incorrectText.isDisplayed());


    }
}
