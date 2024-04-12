package cucumber;

import org.junit.runner.RunWith;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
//@CucumberOptions(features="src/test/java/cucumber",glue="RdAutomation.SeleniumFrameworkDesign.StepDefnitions",
//monochrome=true,  plugin= {"html:target/cucumber.html"})

@CucumberOptions(features="src/test/java/cucumber",glue="RdAutomation.SeleniumFrameworkDesign.StepDefnitions",
monochrome=true, tags = "@Regression", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}



