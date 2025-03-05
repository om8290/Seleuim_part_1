package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignupPage {
    private WebDriver driver;
    private WebDriverWait wait; // Declare explicit wait

    // Locators for the sign-up form elements
    private By usernameField = By.xpath("//input[@name='username']");
    private By passwordField = By.xpath("//input[@name='password']"); // Ensure "password" is lowercase if needed
    private By submitButton = By.xpath("//button[@type='submit']");

    // Constructor
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait timeout 10 sec
    }

    // Method to perform sign-up
    public void signUp(String username, String password) {
        // Wait for the username field to be visible, then enter text
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        userField.sendKeys(username);

        // Wait for the password field to be visible, then enter text
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passField.sendKeys(password);

        // Wait for the submit button to be clickable, then click
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitBtn.click();
    }
}
