package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testdata.Item;


public class SearchResultPage extends BasePage {
    private final By minPriceFilterInput = By.cssSelector("input[data-testid=iptSRPMinPriceFilter]");
    private final By maxPriceFilterInput = By.cssSelector("input[data-testid=iptSRPMaxPriceFilter]");
    private final By mallFilter = By.cssSelector("input[value='Mall']+span");
    private final By sortSelectButton = By.cssSelector("button[data-unify=Select]");
    private final By lowestPriceOption = By.cssSelector("button[data-item-text='Harga Terendah']");
    private final By itemDetail = By.xpath("//div[@class='y-oybT3IAd310DVdH3OwVg== ']");


    // Constructor
    public SearchResultPage(WebDriver driver) {
        super(driver);  // Call the constructor of BasePage
    }

    public void load() {
        // In this case, we assume the search result page is already loaded by the time
        // this object is instantiated (after performing the search in HomePage).
    }

    public void setMinPriceFilter(String minPrice) {
        driver.findElement(minPriceFilterInput).sendKeys(minPrice + Keys.RETURN);
    }

    public void setMaxPriceFilter(String maxPrice) {
        driver.findElement(maxPriceFilterInput).sendKeys(maxPrice + Keys.RETURN);
    }

    public void filterByMall() {
        driver.findElement(mallFilter).click();
    }

    public void sortByLowestPrice() {
        driver.findElement(sortSelectButton).click();
        driver.findElement(lowestPriceOption).click();
    }

    public List<testdata.Item> getAllItemDetails() {
        List<WebElement> itemElements = driver.findElements(itemDetail);
        List<testdata.Item> itemDetails = new ArrayList<>();
        for (WebElement itemElement : itemElements) {
                String raw = itemElement.getText();
                if (raw == null || raw.isBlank()) {
                    continue;
                }
                String[] tokens = raw.split("\n");

                // Map tokens -> name, price, rating, others
                String name = tokens.length > 0 ? tokens[0] : "";
                String price = tokens.length > 1 ? tokens[1] : "";
                String rating = tokens.length > 2 ? tokens[2] : "";
                String others = "";
                if (tokens.length > 3) {
                    others = String.join(" ", Arrays.copyOfRange(tokens, 3, tokens.length));
                }

                itemDetails.add(new Item(name, price, rating, others));
        }
        return itemDetails;
    }
}

