import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MSiteTest {

    private static final String URL = "http://127.0.0.1:4444/wd/hub";
    private AppiumDriver <WebElement> driver;

    @Before
    public void prepare() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0.2");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "02d72d1d174dd4cb");
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        driver = new AndroidDriver<>(new URL(URL), desiredCapabilities);
    }

    @Given("^google search page$")
    public void googlePage() throws InterruptedException {
        driver.get("https://www.google.com/");
    }

    @When("^user search for selenium$")
    public void searchForSelenium() {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("selenium");
        element.submit();
    }

    @Then("^user should see result page$")
    public void assertResultPage() {
        driver.findElement(By.id("ires")).isDisplayed();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
