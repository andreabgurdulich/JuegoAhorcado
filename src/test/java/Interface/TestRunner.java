package Interface;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
  features = "Features"//folder name
		  ,glue={"StepDefinition"} //package name for step def
)

public class TestRunner {

}
