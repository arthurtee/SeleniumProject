package Tests;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.SearchResultPage;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected SearchResultPage searchResultPage;

    @Before
    public void setup() {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Initialize page objects
        homePage = new HomePage(driver);  // Ensure homePage is initialized
        searchResultPage = new SearchResultPage(driver);

    }

    @After
    public void tearDown() {
        // Cleanup after tests
        if (driver != null) {
            driver.quit();  // Close all open browser windows
        }
    }
}
