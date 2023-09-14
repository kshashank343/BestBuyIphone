package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BestBuySteps {
    private static WebDriver driver;

    @Given("^I open the BestBuy website$")
    public void i_open_the_BestBuy_website() {
        driver = new EdgeDriver();
        driver.get("https://www.bestbuy.com/");
        WebElement country = driver.findElement(By.xpath("//div[@lang='en']//img[@alt='United States']"));
        country.click();
    }

    @When("^I search for '(.*)' in the top search bar and click the search icon$")
    public void i_search_for_in_the_top_search_bar_and_click_the_search_icon(String searchText) {
        WebElement searchInput = driver.findElement(By.id("gh-search-input"));
        searchInput.sendKeys(searchText);
        WebElement searchIcon = driver.findElement(By.xpath("//span[@class='header-search-icon']//*[name()='svg']"));
        searchIcon.click();
    }

    @And("^I wait for the search results to load$")
    public void i_wait_for_the_search_results_to_load() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".all-categories-link")));
    }
    

    @And("^I find an item with description '(.*)', model '(.*)', and price '(.*)'$")
    public void i_find_an_item_with_description_model_and_price(String description, String model, String price) {
    	WebElement itemDescription = driver.findElement(By.xpath("//a[contains(text(), '" + description + "')]"));
        WebElement itemModel = driver.findElement(By.xpath("//span[normalize-space()= '" + model + "']"));
        WebElement itemPrice = driver.findElement(By.xpath("//span[normalize-space()= '" + price + "']"));
        
        String actualDescription = itemDescription.getText();
        String actualModel = itemModel.getText();
        String actualPrice = itemPrice.getText();
        
        assertTrue(actualDescription.contains(description));
        assertTrue(actualModel.contains(model));
        assertTrue(actualPrice.contains(price));
        
       driver.findElement(By.xpath("//a[contains(text(), '" + description + "')]")).click();
    }

    @And("^I click on 'Add to Cart'$")
    public void i_click_on_Add_to_Cart() {
    	WebElement addToCart = driver.findElement(By.xpath("//button[normalize-space()='Add to Cart']"));
    	addToCart.click();
    }

    @Then("^I verify that a popup appears with the text '(.*)'$")
    public void i_verify_that_a_popup_appears_with_the_text(String popupText) {
    	WebElement itemAdded = driver.findElement(By.xpath("//span[contains(text(), '" + popupText + "')]"));
    	
    	String descAddedItem = itemAdded.getText();
    	
    	assertTrue(descAddedItem.contains(popupText));
    }

    @And("^I verify that I am on the Cart page by validating the presence of 'Order Summary' and the 'Check out' button$")
    public void i_verify_that_i_am_on_the_Cart_page_by_validating_the_presence_of_Order_Summary_and_the_Check_out_button() {
    	WebElement orderSummary = driver.findElement(By.xpath("//h2[contains(text(),'Order Summary')]"));
    	String orderSummaryText = orderSummary.getText();
    	assertTrue(orderSummaryText.contains("Order Summary"));
    	
    	WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"));
    	String checkoutButtonText = checkoutButton.getText();
    	assertTrue(checkoutButtonText.contains("Checkout"));
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}