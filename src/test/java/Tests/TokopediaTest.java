package Tests;

import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.SearchResultPage;

public class TokopediaTest {
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultPage searchResultPage;
    private static final String BASE_URL = "https://www.tokopedia.com/";
    private static final String SEARCH_KEYWORD = "iphone 16 pro";
    private static final String MIN_PRICE = "100000";
    private static final String MAX_PRICE = "30000000";

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
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Wait up to maximum 30 seconds for each object. (To be decided based on your specs)®
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Initialize page objects
        homePage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    private void navigateAndSearch(String keyword) {
         driver.get(BASE_URL);
         homePage.searchProduct(keyword);
    }

    @Test
    public void testSearchForIphone16Pro() {
        navigateAndSearch(SEARCH_KEYWORD);
        
        // Wait for the result header by data-testid
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("[data-testid='result-header-wrapper']")));

        // Assert the element is present and visible
        org.junit.Assert.assertTrue("result-header-wrapper should be visible", header.isDisplayed());
    }

    @Test
    public void testPrintFilteredProducts() {
        navigateAndSearch(SEARCH_KEYWORD);

        // Filter price with min. 100,000 and max 30,000,000
        searchResultPage.setMinPriceFilter("100000");
        searchResultPage.setMaxPriceFilter("30000000");

        // Filter only from the "Official store"
        searchResultPage.filterByMall();

        // Sort result from the lowest price
        searchResultPage.sortByLowestPrice();

        int num = 1;
            // Get all item names
            List<testdata.Item> searchedItemNames = searchResultPage.getAllItemDetails();

            for (testdata.Item item : searchedItemNames) {
                System.out.println("\n" + num + ")\nItem: " + item.getName() + 
                    "\nPrice: " + item.getPrice() );
                num++;
            }

            System.out.println("\nPage elements count: " + searchedItemNames.size());        

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}

