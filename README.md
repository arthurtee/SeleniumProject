# SeleniumProject

This is a selenium project completed by me with [ChatGPT](https://chat.openai.com/) using [Page Object Design Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/).

Below are the automated scenarios located [here](src/main/java/Tests/TokopediaTest.java):

1) Go to tokopedia.com, search "iphone 14 pro".
2) Filter the price with min. 100.000, max 30.000.000, filter only from the “Official Store”, sort from the “Harga Terendah”.
3) Obtain all the item names from page 1 until 3 and print on the terminal. 

## How to run?
* Select your preferred development environment. This project is developed using:
  * [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=mac) as the Java IDE. 
  * Maven as a build tool. 
  * Openjdk version "20.0.1".
* Install [Selenium library](https://www.selenium.dev/documentation/webdriver/getting_started/install_library/).
* Download [ChromeDriver](https://chromedriver.chromium.org/downloads).
* After cloning this repository, update the code with your own chrome driver directory. 
* Press Ctrl+R or click the green arrow button in the gutter to run the code.
