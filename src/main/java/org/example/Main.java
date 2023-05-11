package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Starting Driver (REPLACE BELOW with your own chrome driver directory)
        System.setProperty("webdriver.chrome.driver","/Users/att/Downloads/chromedriver_mac64/chromedriver");

        // Chrome browser configurations
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");

        WebDriver driver= new ChromeDriver(options);
        driver.manage().window().maximize();

        // Wait up to maximum 30 seconds for each object. (To be decided based on your specs)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Go to tokopedia website
        driver.get("https://www.tokopedia.com/");

        // Search for iphone 14 pro
        driver.findElement(By.cssSelector("input[data-unify=Search]")).sendKeys("iphone 14 pro" + Keys.RETURN);

        // Filter price with min. 100.000, max 30.000.000
        driver.findElement(By.cssSelector("input[data-testid=iptSRPMinPriceFilter]")).sendKeys("100000" + Keys.RETURN);
        driver.findElement(By.cssSelector("input[data-testid=iptSRPMaxPriceFilter]")).sendKeys("30000000" + Keys.RETURN);

        // Filter only from the "Official store"
        driver.findElement(By.cssSelector("input[value='Official Store']+span")).click();

        // Sort result from the lowest price
        driver.findElement(By.cssSelector("button[data-unify=Select]")).click();
        driver.findElement(By.cssSelector("button[data-item-text='Harga Terendah']")).click();

        // Logic to print all the item names from page 1 until 3
        List <String> allItemNames = new ArrayList<>();
        int num=0;

        // Pagination Loop for 1-3
        for (int i=1; i<=3; i++){

            // Scroll to page most bottom
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

            if (i>1) {
                // Click on next page
                driver.findElement(By.cssSelector("button[aria-label='Laman " + i + "']")).click();
            }

                // Loop to get all item names
                List<WebElement> searchedItemNames = driver.findElements(By.cssSelector("[data-testid='spnSRPProdName']"));

                for (WebElement itemName : searchedItemNames) {
                    num++;
                    System.out.println("\n" + num +". Item name: " + itemName.getText());
                    allItemNames.add(itemName.getText());
                }
                System.out.println("\n Page "+i+" elements count: " + searchedItemNames.size());
        }

        driver.quit();
    }
}