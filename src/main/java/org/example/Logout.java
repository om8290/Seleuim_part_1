package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Logout {
    private final WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By logoutDropdown = By.xpath("//span[@class='oxd-userdropdown-tab']");
    private By logoutButton = By.xpath("(//a[@role='menuitem'])[4]");
    private By loginTitle = By.xpath("//h5[@class='oxd-text oxd-text--h5 orangehrm-login-title']");

    public Logout(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String performLogout() {
        // Click on the user dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(logoutDropdown));
        dropdown.click();

        // Click on the logout button
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        btn.click();

        // Wait for the login page title to be visible and fetch its text
        WebElement loginText = wait.until(ExpectedConditions.visibilityOfElementLocated(loginTitle));
        return loginText.getText();
    }
}
