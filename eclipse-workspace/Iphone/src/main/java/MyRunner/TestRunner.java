package MyRunner;


import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/main/java/Features/BestBuy.feature",
        glue = {"stepDefinitions"},
    plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner extends io.cucumber.testng.AbstractTestNGCucumberTests {
}