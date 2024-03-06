package Testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features =".\\src\\test\\resources",
		glue ="com.ajioTesttt",
		tags = "@ajio or @facebook",
		//tags = "@facebook",
		plugin = {"html:target/report1.html","json:target/report2.json"},
		dryRun=false,
		monochrome=true	
		)
public class TestRunner {

}
