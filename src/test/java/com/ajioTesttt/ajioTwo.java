package com.ajioTesttt;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ajioTwo {
	public static WebDriver driver;
	public static Actions actions;
	public static Select s;
	
	@Given("Launch Ajio {string}")
	public void launch_ajio(String url) {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		options.addArguments("start-maximized");
		driver=new EdgeDriver(options);
		actions = new Actions(driver);
		driver.get(url);	
	}

	@Given("Navigate to men and click on Footware")
	public void navigate_to_men_and_click_on_footware() {
		WebElement men = driver.findElement(By.xpath("//span[text()='MEN']"));//
		   actions.moveToElement(men).perform();
		   WebElement Footwear = driver.findElement(By.xpath("//strong[text()='FOOTWEAR']"));
		   actions.click(Footwear).perform();
	}

	@When("User clicks on sort by and selects option in dropdown with one dim map")
	public void user_clicks_on_sort_by_and_selects_option_in_dropdown_with_one_dim_map(DataTable dataTable) throws Exception {
		Map<String, String> asMap = dataTable.asMap(String.class, String.class);
		String object = asMap.get("sort1");


		WebElement shortt = driver.findElement(By.xpath("//select[@id='sortBy']"));
		s = new Select (shortt); 
		Thread.sleep(2000);
		List<WebElement> options = s.getOptions();
		for (WebElement option:options) {
			String text = option.getText();
			if(text.contains(object)) {
			s.selectByVisibleText(text);
		}	
		}
		}

	@Then("Validate the price list")
	public void validate_the_price_list() throws Exception {
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='price  ']"));
		
		for (int i = 0; i < price.size()-1; i++) {
			Thread.sleep(2000);
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			String prices=price.get(i).getText().substring(1);	
			String prices1=price.get(i+1).getText().substring(1);
			
			String rate = prices.replaceAll(",","");
			String rate1 = prices1.replaceAll(",","");
			
			int value1 = Integer.parseInt(rate);
			int value2 = Integer.parseInt(rate1);
			if(value1>value2) {
				System.out.println(value1+" : Sorted List");
			}
		}
	
	}


}