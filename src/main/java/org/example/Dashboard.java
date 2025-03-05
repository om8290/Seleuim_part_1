package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Dashboard {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By claimMenuItem = By.xpath("(//li[@class='oxd-main-menu-item-wrapper'])[11]");
    private By claimPageTab = By.xpath("(//a[@class='oxd-topbar-body-nav-tab-item'])[1]");
    private By eventDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    private By currencyDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    private By dropdownOptions = By.xpath("//div[@role='listbox']//span");
    private By remarkTextarea = By.xpath("//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']");
    private By submitButton = By.xpath("//button[@type='submit']");
    private By myClaimTab = By.xpath("(//a[@class='oxd-topbar-body-nav-tab-item'])[2]");
    private By claimTextColumn = By.xpath("//div[@class='oxd-table-card']//div/div[3]");

    public Dashboard(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToClaimPage() {
        // Click on the 'Claim' menu item
        WebElement claimMenu = wait.until(ExpectedConditions.elementToBeClickable(claimMenuItem));
        claimMenu.click();

        // Click on the 'Claim Page' tab
        WebElement claimPgTab = wait.until(ExpectedConditions.elementToBeClickable(claimPageTab));
        claimPgTab.click();

        // Select 'Travel Allowance' from the 'Event' dropdown
        selectOptionFromDropdown(eventDropdown, "Travel Allowance");

        // Select 'Indian Rupee' from the 'Currency' dropdown
        selectOptionFromDropdown(currencyDropdown, "Indian Rupee");

        // Enter text into the 'Remark' textarea
        WebElement remark = wait.until(ExpectedConditions.visibilityOfElementLocated(remarkTextarea));
        String remarkText = "try try but dont cry";
        remark.sendKeys(remarkText);

        // Click Submit Button
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submit.click();

        // Click on 'My Claim' Tab
        WebElement myClaim = wait.until(ExpectedConditions.elementToBeClickable(myClaimTab));
        myClaim.click();

        // Wait for Claim Records to Load
        wait.until(ExpectedConditions.visibilityOfElementLocated(claimTextColumn));

        // Verify the Claim Entry
        List<WebElement> claims = driver.findElements(claimTextColumn);
        boolean claimFound = false;

        for (WebElement claim : claims) {
            if (claim.getText().equals(remarkText)) {
                claimFound = true;
                System.out.println("Claim verified successfully!");
                break;
            }
        }

        if (!claimFound) {
            System.out.println("Claim not found!");
        }
    }

    private void selectOptionFromDropdown(By dropdownLocator, String optionText) {
        // Click the dropdown to display options
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
        dropdown.click();

        // Wait for options to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOptions));

        // Retrieve all options
        List<WebElement> options = driver.findElements(dropdownOptions);

        // Iterate through options and click the matching one
        for (WebElement option : options) {
            if (option.getText().equals(optionText)) {
                option.click();
                break;
            }
        }
    }
}
