package com.AutomationExercises.test.TestCase_1;

import com.AutomationExercises.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCase4 {


    /*
    * Test Case 4: Logout User
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Signup / Login' button
            5. Verify 'Login to your account' is visible
            6. Enter correct email address and password
            7. Click 'login' button
            8. Verify that 'Logged in as username' is visible
            9. Click 'Logout' button
            10. Verify that user is navigated to login page
* */
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
    public void tc04() throws InterruptedException {

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

        //6. Enter correct email address and password
        WebElement email = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        email.sendKeys("Hasan@gmail.com");

        driver.findElement(By.xpath("//input[@type='password']"))
        .sendKeys("Hasan"+Keys.ENTER);

        //8. Verify that 'Logged in as username' is visible
        WebElement loggedText = driver.findElement(By.xpath("//i[@class='fa fa-user']/.."));
        System.out.println("loggedText.getText() = " + loggedText.getText());
        Assert.assertTrue(loggedText.isDisplayed());

        //9. Click 'Logout' button
        WebElement logOutBtn = driver.findElement(By.linkText("Logout"));
        logOutBtn.click();

        //10. Verify that user is navigated to login page
        WebElement loginText2 = driver.findElement(By.xpath("//*[text()='Login to your account']"));
        Assert.assertTrue(loginText2.isDisplayed());



    }
}
