package com.AutomationExercises.test.TestCase_1;

import com.AutomationExercises.utilities.WebDriverFactory;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase1 {
        /*
                * Test Case 1: Register User
                1. Launch browser
                2. Navigate to url 'http://automationexercise.com'
                3. Verify that home page is visible successfully
                4. Click on 'Signup / Login' button
                5. Verify 'New User Signup!' is visible
                6. Enter name and email address
                7. Click 'Signup' button
                8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
                9. Fill details: Title, Name, Email, Password, Date of birth
                10. Select checkbox 'Sign up for our newsletter!'
                11. Select checkbox 'Receive special offers from our partners!'
                12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
                13. Click 'Create Account button'
                14. Verify that 'ACCOUNT CREATED!' is visible
                15. Click 'Continue' button
                16. Verify that 'Logged in as username' is visible
                17. Click 'Delete Account' button
                18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button*/


    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver("chrome");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();

    }

    @Test
    public void tc01() throws InterruptedException {
        //1. Launch browser

        driver.navigate().to("http://automationexercise.com");
        Thread.sleep(1000);
        driver.manage().window().maximize();

        //2. Navigate to url 'http://automationexercise.com'
        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);
        String expectedUrl = "http://automationexercise.com/";

        //3. Verify that home page is visible successfully
        if (currentURL.equals(expectedUrl)) {
            System.out.println("Test PASSED");
        } else {
            System.out.println("Test FAILED");
        }

        //4. Click on 'Signup / Login' button
        WebElement loginButton = driver.findElement(By.partialLinkText("Signup / Login"));
        loginButton.click();

        //5. Verify 'New User Signup!' is visible
        WebElement newUserSignup = driver.findElement(By.xpath("//h2[text()='New User Signup!']"));
        System.out.println("newUserSignup.getText() = " + newUserSignup.getText());

        Faker faker = new Faker();
        //6. Enter name and email address
        WebElement name = driver.findElement(By.xpath("//input[@placeholder='Name']"));
        name.sendKeys(faker.name().firstName());
        WebElement email = driver.findElement(By.xpath("(//input[@placeholder='Email Address'])[2]"));
        email.sendKeys(faker.internet().emailAddress());

        //7. Click 'Signup' button
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).submit();

        //8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        System.out.println(driver.findElement(By.xpath("//b[text()='Enter Account Information']")).getText());

        //9. Fill details: Title, Name, Email, Password, Date of birth

        driver.findElement(By.cssSelector("#id_gender1")).click();
        driver.findElement(By.cssSelector("#name")).sendKeys(faker.name().firstName());
        driver.findElement(By.cssSelector("#password")).sendKeys(faker.internet().password());
        WebElement day = driver.findElement(By.cssSelector("#days"));
        day.sendKeys("5");

        WebElement months = driver.findElement(By.xpath("(//select/option[@value='5'])[2]"));
        months.click();

        WebElement year = driver.findElement(By.xpath("//select[starts-with(@id,'years')]/option[5]"));
        year.click();

        //10. Select checkbox 'Sign up for our newsletter!'

        WebElement checkbox1 = driver.findElement(By.xpath("//input[@id='newsletter']"));
        checkbox1.click();
        Thread.sleep(2000);

        //11. Select checkbox 'Receive special offers from our partners!'
        WebElement checkbox2 = driver.findElement(By.xpath("//input[@id='optin']"));
        checkbox2.click();
        Thread.sleep(2000);


        //12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        Actions actions=new Actions(driver);
        WebElement firstName = driver.findElement(By.cssSelector("#first_name"));
        firstName.sendKeys(faker.name().firstName());
        WebElement lastName = driver.findElement(By.xpath("//input[@id='last_name']"));
        lastName.sendKeys(faker.name().lastName());
        WebElement company = driver.findElement(By.xpath("//input[@id='company']"));
        company.sendKeys("ABCD COMPANY");
        WebElement adress = driver.findElement(By.xpath("//input[@id='address1']"));
        adress.sendKeys(faker.address().streetAddress());
        WebElement country = driver.findElement(By.xpath("//option[@value='India']"));
        country.click();
        WebElement state = driver.findElement(By.cssSelector("#state"));
        state.sendKeys(faker.address().state());
        WebElement city = driver.findElement(By.cssSelector("#city"));
        city.sendKeys(faker.address().city());
        WebElement zipcode = driver.findElement(By.cssSelector("#zipcode"));
        zipcode.sendKeys(faker.address().zipCode());
        WebElement mobileNumber = driver.findElement(By.id("mobile_number"));
        mobileNumber.sendKeys(faker.phoneNumber().cellPhone());

        //13. Click 'Create Account button'
        WebElement submitButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
        submitButton.submit();

        //14. Verify that 'ACCOUNT CREATED!' is visible

        String expected = "ACCOUNT CREATED!";

        WebElement accountCreated = driver.findElement(By.xpath("//b[text()='Account Created!']"));
        Assert.assertEquals(accountCreated.getText(),expected);

        //15. Click 'Continue' button
        WebElement clickContinue = driver.findElement(By.xpath("//a[@data-qa='continue-button']"));
        clickContinue.click();
        Thread.sleep(2000);
        driver.navigate().refresh();
        WebElement close = driver.findElement(By.xpath("//span[@class='ns-wy7yg-e-18']"));

        Thread.sleep(1000);
        close.click();



        //16. Verify that 'Logged in as username' is visible

        String expected2 = "Logged in as JamieLanette";

        WebElement loggedUserName = driver.findElement(By.xpath("//*[text()=' Logged in as ']/.."));
        System.out.println("loggedUserName.getText() = " + loggedUserName.getText());
        Assert.assertEquals(loggedUserName.getText(),expected2);


//        actions.click(firstName     )
//                .sendKeys(faker.name().firstName())
//                .sendKeys(Keys.TAB)
//                .sendKeys(faker.name().lastName())
//                .sendKeys(Keys.TAB)
//                .sendKeys("ABC")
//                .sendKeys().perform();



        //17. Click 'Delete Account' button
        //18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button*/



    }


}
