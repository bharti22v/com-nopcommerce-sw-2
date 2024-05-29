package testsuit;

import browserfactory.BaseTest;
import org.bouncycastle.oer.its.etsi102941.Url;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        //find the login link and click on login link
        WebElement loginLink =driver.findElement(By.linkText("Log in"));
        loginLink.click();
        String expectedText = "Welcome,Please Sign In";
        WebElement welcomeTextElement = driver.findElement(By.xpath("//h1"));
       String actualText =  welcomeTextElement.getText();
        Assert.assertEquals("Not redirected to login page", expectedText,actualText);
    }
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials(){
        //find login link and click on login link
        WebElement loginLink =  driver.findElement(By.linkText("Log in"));
        loginLink.click();
        //find and fill the email field
        WebElement userName = driver.findElement(By.className("email"));
        userName.sendKeys("bhartik22@gmail.com");
        //find and fill the password filed
        WebElement passWord = driver.findElement(By.id("Password"));
        passWord.sendKeys("MummyPapa123");
        //click on login button
        WebElement loginbutton =  driver.findElement(By.xpath("//button[@class='button-1 login-button']"));
        loginbutton.click();
        String expectedMessage = "Log out";
        String actualMessage = driver.findElement(By.linkText("Log out")).getText();
        Assert.assertEquals(expectedMessage,actualMessage);
    }
    @Test
    public void verifyTheErrorMessage(){
        //Find the login link and click on login link element
        WebElement loginlink = driver.findElement(By.linkText("Log in"));
        loginlink.click();
        //Find the Email Element and type the Email address to email field.
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys("bhartk22@gmail.com");
        //Find the password field and type the password into password field
        driver.findElement(By.name("Password")).sendKeys("MummyPapa123");
        //Find Login btn Element and click
        driver.findElement(By.xpath("//button[text() = 'Log in']")).click();
        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found";
        String actualErrorMessage = driver.findElement(By.xpath("//div[@class = 'message-error validation-summary-errors']")).getText();
        Assert.assertEquals("Error message not displayed",expectedErrorMessage,actualErrorMessage);
    }
    @After
    //close the browser
    public void tearDown(){
        closeBrowser();
      }
    }
