package Tests;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testdata.Item;


public class TokopediaTest extends BaseTest {
    private static final String SEARCH_KEYWORD = "iphone 16 pro";

    @Test
    public void testSearchForIphone16Pro() {

        homePage.load();
        homePage.searchProduct(SEARCH_KEYWORD);
        
        // Wait for the result header by data-testid
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("[data-testid='result-header-wrapper']")));

        // Assert the element is present and visible
        Assert.assertTrue("result-header-wrapper should be visible", header.isDisplayed());
    }

    @Test
    public void testPrintFilteredProductsNotEmpty() {
        homePage.load();
        homePage.searchProduct(SEARCH_KEYWORD);

        // Filter price with min. 100,000 and max 30,000,000
        searchResultPage.setMinPriceFilter("100000");
        searchResultPage.setMaxPriceFilter("30000000");

        // Filter only from the "Official store"
        searchResultPage.filterByMall();

        // Sort result from the lowest price
        searchResultPage.sortByLowestPrice();

        List<Item> searchedItems = searchResultPage.getAllItemDetails();
        Item.printItems(searchedItems); 

        Assert.assertFalse("Items is empty", searchedItems.isEmpty());
    }

}

