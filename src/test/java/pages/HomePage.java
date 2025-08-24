package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By searchInput = By.cssSelector("input[data-unify=Search]");
    
    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);  // Pass WebDriver to the BasePage constructor
    }

    // Implement the load() method for HomePage-specific actions
    public void load() {
        driver.get("https://www.tokopedia.com/");
    }

    // Specific action for searching a product
    public void searchProduct(String productName) {
        driver.findElement(searchInput).sendKeys(productName + Keys.RETURN);
    }
}

