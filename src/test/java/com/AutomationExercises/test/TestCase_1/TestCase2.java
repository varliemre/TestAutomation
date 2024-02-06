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

public class TestCase2 {

    /*
            Test Case 2: Login User with correct email and password
                1. Launch browser
                2. Navigate to url 'http://automationexercise.com'
                3. Verify that home page is visible successfully
                4. Click on 'Signup / Login' button
                5. Verify 'Login to your account' is visible
                6. Enter correct email address and password
                7. Click 'login' button
                8. Verify that 'Logged in as username' is visible
                9. Click 'Delete Account' button
                10. Verify that 'ACCOUNT DELETED!' is visible
     */
    WebDriver driver;
    WebDriverWait wait;
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
    public void tc02() {
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");

        //3. Verify that home page is visible successfully
        WebElement logo = driver.findElement(By.xpath("(//a[@href='/'])[1]"));
        Assert.assertTrue(logo.isDisplayed(),"home page is verified");

        //4. Click on 'Signup / Login' button
        WebElement loginButton = driver.findElement(By.partialLinkText("Signup / Login"));
        loginButton.click();

        //5. Verify 'Login to your account' is visible
        WebElement loginText = driver.findElement(By.xpath("//*[text()='Login to your account']"));
        Assert.assertTrue(loginText.isDisplayed());

        //6. Enter correct email address and password
        Faker faker = new Faker();

        Actions actions=new Actions(driver);
        WebElement email = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        actions.click(email)
                .sendKeys("emre123@gmail.com")
                .sendKeys(Keys.TAB)
                .sendKeys("emre123")
                //7. Click 'login' button
                .sendKeys(Keys.ENTER)
                .perform();

        //8. Verify that 'Logged in as username' is visible
        WebElement loggedText = driver.findElement(By.xpath("//i[@class='fa fa-user']/.."));
        System.out.println("loggedText.getText() = " + loggedText.getText());
        Assert.assertTrue(loggedText.isDisplayed());

        //9. Click 'Delete Account' button
        WebElement deleteAccount = driver.findElement(By.xpath("//a[@href='/delete_account']"));
        deleteAccount.click();

        driver.navigate().back();
        driver.navigate().forward();

        //10. Verify that 'ACCOUNT DELETED!' is visible
        WebElement deleted = driver.findElement(By.xpath("//b[text()='Account Deleted!']"));
        System.out.println("deleted.isDisplayed() = " + deleted.isDisplayed());
        Assert.assertTrue(deleted.isDisplayed());


    }
}
