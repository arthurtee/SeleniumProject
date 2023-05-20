package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    private By minPriceFilterInput = By.cssSelector("input[data-testid=iptSRPMinPriceFilter]");
    private By maxPriceFilterInput = By.cssSelector("input[data-testid=iptSRPMaxPriceFilter]");
    private By officialStoreFilter = By.cssSelector("input[value='Official Store']+span");
    private By sortSelectButton = By.cssSelector("button[data-unify=Select]");
    private By lowestPriceOption = By.cssSelector("button[data-item-text='Harga Terendah']");
    private By paginationButton(String pageNumber) {
        return By.cssSelector("button[aria-label='Laman " + pageNumber + "']");
    }
    private By itemNames = By.cssSelector("[data-testid='spnSRPProdName']");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setMinPriceFilter(String minPrice) {
        driver.findElement(minPriceFilterInput).sendKeys(minPrice + Keys.RETURN);
    }

    public void setMaxPriceFilter(String maxPrice) {
        driver.findElement(maxPriceFilterInput).sendKeys(maxPrice + Keys.RETURN);
    }

    public void filterByOfficialStore() {
        driver.findElement(officialStoreFilter).click();
    }

    public void sortByLowestPrice() {
        driver.findElement(sortSelectButton).click();
        driver.findElement(lowestPriceOption).click();
    }

    public void goToPage(int pageNumber) {
        driver.findElement(paginationButton(String.valueOf(pageNumber))).click();
    }

    public List<String> getAllItemNames() {
        List<WebElement> itemElements = driver.findElements(itemNames);
        List<String> itemNames = new ArrayList<>();
        for (WebElement itemElement : itemElements) {
            itemNames.add(itemElement.getText());
        }
        return itemNames;
    }
}

