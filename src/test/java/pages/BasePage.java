package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;

    // Constructor to initialize the WebDriver
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Common method to wait for an element to be visible
    public void waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Common method to click an element
    public void clickElement(By locator) {
        waitForElement(locator);  // Wait for element to be visible before clicking
        WebElement element = driver.findElement(locator);
        element.click();
    }

    // Common method to send keys to an input field
    public void sendKeysToElement(By locator, String text) {
        waitForElement(locator);  // Wait for element to be visible
        WebElement element = driver.findElement(locator);
        element.sendKeys(text);
    }

    // Abstract method to be implemented by each page class
    public void load(String url) {
        driver.get(url);
    }    

    public WebDriver getDriver() {
        return driver;
    }
}
