package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.SearchResultPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.*;

public class TokopediaTest {
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultPage searchResultPage;

    @Before
    public void setup() {
        // Set ChromeDriver path automatically using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Chrome browser configurations
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Wait up to maximum 30 seconds for each object. (To be decided based on your specs)®
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Initialize page objects
        homePage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @Test
    public void testSearchProduct() {
        // Go to Tokopedia website
        driver.get("https://www.tokopedia.com/");

        // Search for iPhone 14 Pro
        homePage.searchProduct("iphone 14 pro");
    }

    @Test
    public void testFilterOnSearchedProducts() {
        // Go to Tokopedia website
        driver.get("https://www.tokopedia.com/");

        // Search for iPhone 14 Pro
        homePage.searchProduct("iphone 14 pro");

        // Filter price with min. 100,000 and max 30,000,000
        searchResultPage.setMinPriceFilter("100000");
        searchResultPage.setMaxPriceFilter("30000000");

        // Filter only from the "Official store"
        searchResultPage.filterByOfficialStore();

        // Sort result from the lowest price
        searchResultPage.sortByLowestPrice();
    }

    @Test
    public void testPrintFirst3PageOfFilteredProducts() {
        // Go to Tokopedia website
        driver.get("https://www.tokopedia.com/");

        // Search for iPhone 14 Pro
        homePage.searchProduct("iphone 14 pro");

        // Filter price with min. 100,000 and max 30,000,000
        searchResultPage.setMinPriceFilter("100000");
        searchResultPage.setMaxPriceFilter("30000000");

        // Filter only from the "Official store"
        searchResultPage.filterByOfficialStore();

        // Sort result from the lowest price
        searchResultPage.sortByLowestPrice();

        // Print all the item names from page 1 until 3
        List<String> allItemNames = new ArrayList<>();
        int num = 0;

        for (int i = 1; i <= 3; i++) {
            // Scroll to the bottom of the page
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

            if (i > 1) {
                // Go to the next page
                searchResultPage.goToPage(i);
            }

            // Get all item names
            List<String> searchedItemNames = searchResultPage.getAllItemNames();

            for (String itemName : searchedItemNames) {
                num++;
                System.out.println("\n" + num + ". Item name: " + itemName);
                allItemNames.add(itemName);
            }
            System.out.println("\nPage " + i + " elements count: " + searchedItemNames.size());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}

