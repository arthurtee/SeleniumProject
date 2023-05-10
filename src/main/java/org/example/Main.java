package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.printf("Hello and welcome!");

        // Starting Driver
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

        // Go to tokopedia website
        driver.get("https://www.tokopedia.com/");

        // Search for iphone 14 pro
        //driver.findElement(By.xpath("//input[@data-unify='Search']")).sendKeys("iphone 14 pro"+ Keys.RETURN);
        driver.findElement(By.cssSelector("input[data-unify=Search]")).sendKeys("iphone 14 pro" + Keys.RETURN);

        // Wait up to maximum 30 seconds for each objects
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.findElement(By.cssSelector("input[value='Official Store']+span")).click();

        // Filter price with min. 100.000, max 30.000.000
        driver.findElement(By.cssSelector("input[data-testid=iptSRPMinPriceFilter]")).sendKeys("100000" + Keys.RETURN);
        driver.findElement(By.cssSelector("input[data-testid=iptSRPMaxPriceFilter]")).sendKeys("30000000" + Keys.RETURN);

        // Sort result from the lowest price
        driver.findElement(By.cssSelector("button[data-unify=Select]")).click();
        driver.findElement(By.cssSelector("button[data-item-text='Harga Terendah']")).click();

        List <String> allItemNames = new ArrayList<>();
        int num=0;

        // Pagination Loop
        for (int i=1; i<=3; i++){

            // Scroll to page most bottom
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(3000);

            if (i>1) {
                // Click on next page
                driver.findElement(By.cssSelector("button[aria-label='Laman " + i + "']")).click();
            }

                // Loop to get all item names
                List<WebElement> SearchedItemNames = driver.findElements(By.cssSelector("[data-testid='spnSRPProdName']"));

                for (WebElement itemName : SearchedItemNames) {
                    num++;
                    System.out.println("\n" + num +". Item name: " + itemName.getText());
                    allItemNames.add(itemName.getText());
                }
                System.out.println("\n Page "+i+" elements count: " + SearchedItemNames.size());
        }

//        driver.findElement(By.cssSelector("button[aria-label='Laman 2']")).click();
//
//        driver.findElement(By.cssSelector("button[aria-label='Laman 3']")).click();


        driver.quit();

        // Press Ctrl+R or click the green arrow button in the gutter to run the code.
    }
}