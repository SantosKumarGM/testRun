package com.ajioTesttt;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class faceTwo {
	public static WebDriver driver;
	public static Actions actions;
	public static Select s;
	//public static String firstname;
	
	@Given("Launch Facebook {string}")
	public void launch_facebook(String url) {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		options.addArguments("start-maximized");
		driver=new EdgeDriver(options);
		actions = new Actions(driver);
		driver.get(url);
	   
	}

	@And("Navigate to create new account")
	public void navigate_to_create_new_account() throws Exception {
		driver.findElement(By.xpath("//a[text()='Create new account']")).click();
		Thread.sleep(3000);
	
	}
	
	@When("Enter the name {string}")
	public void enter_the_name(String firstname) {
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstname);
	}
	
	@When("Enter the surname {string}")
	public void enter_the_surname(String surname) {
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(surname);
	}
	
	@When("Enter the number {string}")
	public void enter_the_number(String num) {
		driver.findElement(By.xpath("//input[contains(@aria-label,'Mobile')]")).sendKeys(num);
	}
	
	@When("Enter the password {string}")
	public void enter_the_password(String pass) {
		driver.findElement(By.xpath("//input[@id='password_step_input']")).sendKeys(pass);
	}
	
	@When("Enter the Date {string}")
	public void enter_the_date(String date) {
		WebElement day = driver.findElement(By.xpath("//select[@title='Day']"));
		s = new Select (day); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> options = s.getOptions();
		for (WebElement option:options) {
			String text = option.getText();
			if(text.contains(date)) {
			s.selectByVisibleText(text);
			}
			}
	}
	
	@When("Enter the Month {string}")
	public void enter_the_month(String month) {
		WebElement monthh = driver.findElement(By.xpath("//select[@title='Month']"));
		s = new Select(monthh);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> options2 = s.getOptions();
		for (WebElement option1:options2) {
			String text = option1.getText();
			if (text.contains(month)) {
				s.selectByVisibleText(text);	
			}	
		}
	}
	
	@When("Enter the Year {string}")
	public void enter_the_year(String year) {
		WebElement yearr = driver.findElement(By.xpath("//select[@id='year']"));
		s=new Select(yearr);
		List<WebElement> options3 = s.getOptions();
		for(WebElement option3:options3) {
			String text = option3.getText();
			if(text.contains(year)) {
				s.selectByVisibleText(text);
			}
		}
		driver.findElement(By.xpath("//input[@type='radio']/preceding-sibling::label[text()='Male']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@When("Click On Sigh Up")
	public void click_on_sigh_up() throws Exception {
		driver.findElement(By.xpath("//div[@class='_1lch']//preceding::button[text()='Sign Up']")).click();
		Thread.sleep(2000);
	}

    @Then("Take screenshot {string}")
    public void take_screenshot(String firstname) {
	TakesScreenshot t= (TakesScreenshot)driver;
	File f =t.getScreenshotAs(OutputType.FILE);
	File m =new File("C:\\Users\\gmsan\\eclipse-workspace\\java\\AjioTestRunnerTWO\\target"+firstname+".png");
	try {
		FileUtils.copyFile(f, m);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
    @Then("Validate the url")
    public void validate_the_url() throws MalformedURLException, IOException {
    	List<WebElement> links = driver.findElements(By.tagName("link"));
    	Iterator<WebElement> iterator = links.iterator();
    	while(iterator.hasNext()) {
    		WebElement link = iterator.next();
    		String href = link.getAttribute("href");
    		if(href ==null || href.isEmpty()) {
    			System.out.println("Link is not Available");
    		}
    		else if (!href.startsWith("https://www.facebook.com")) {
    			System.out.println("link is not related to facebook: "+href);
    			
    		}
    		else {
    			HttpURLConnection http =(HttpURLConnection)(new URL(href).openConnection());
    			http.setRequestMethod("HEAD");
    			http.connect();
    			int responseCode = http.getResponseCode();
    			if(responseCode==200) {
    				System.out.println("link is not broken : "+href);
    			}
    		}
    	}
    }
    

}