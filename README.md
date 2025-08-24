# SeleniumProject

This is a selenium project to demostrate test automation concepts:
1) [Page Object Design Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/).

Below are the automated scenarios:

1) Go to tokopedia.com, search "iphone 16 pro".
2) Filter the price with min. 100.000, max 30.000.000, filter only from the “Mall”, sort from the “Harga Terendah”.
3) Obtain all the item names an price print on the terminal. 

## How to run?
* Select your preferred development environment. (Visual Studio Code/IntelliJ IDEA)
* Maven as a build tool. 
* Run cmd to setup: `mvn clean install`
* Run all tests: `mvn test`
* Run specific test: `mvn -Dtest=TokopediaTest#testPrintFilteredProductsNotEmpty test`
