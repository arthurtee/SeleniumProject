package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By searchInput = By.cssSelector("input[data-unify=Search]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(String productName) {
        driver.findElement(searchInput).sendKeys(productName + Keys.RETURN);
    }
}

