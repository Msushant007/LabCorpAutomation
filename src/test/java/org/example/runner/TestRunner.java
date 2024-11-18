package org.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features =
        "src/test/java/org/example/features",
        glue = {"org/example/stepdefs"},
        dryRun = false,
        tags = "@Ui or @Api",
        monochrome = true,
        publish = true,
        plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}


               // "pretty", "json:target/myReports/cucumber.json",
        // "pretty", "html:target/cucumber.html"}

)

public class TestRunner extends AbstractTestNGCucumberTests {

}

//mvn exec:java                                  \
//    -Dexec.classpathScope=test                 \
//    -Dexec.mainClass=io.cucumber.core.cli.Main \
//    -Dexec.args="./features/Login.feature --glue stepdefs "
