package org.demo;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.CommandExecutionHelper;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.appium.java_client.MobileCommand.pressKeyCodeCommand;
//import com.browserstack.AppPercySDK;
import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidElement;
public class amazonFireTV {

   public AndroidDriver driver;

    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {

        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
        bstackOptions.putIfAbsent("source", "testng-java:sample-sdk:v1.0");
        capabilities.setCapability("bstack:options", bstackOptions);
        driver = new AndroidDriver(new URL("https://hub.browserstack.com/wd/hub"), capabilities);

    }
    @Test
    public void testt() throws Exception {

        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      //  AppPercySDK.screenshot(driver, "My Screenshot");
        WebElement next_button = (WebElement) wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.View[@content-desc=\"Next\"]")));
        next_button.click();
        Thread.sleep(5000);
        next_button.click();
        Thread.sleep(5000);
        next_button.click();

        WebElement get_started_button = (WebElement) wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("com.example.android.tvleanback:id/button_start")));
        get_started_button.click();

        Thread.sleep(2000);
        WebElement menu_sideitems = (WebElement) wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("com.example.android.tvleanback:id/header_label")));
        menu_sideitems.click();
        Thread.sleep(2000);

        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(22));
        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(23));

        Thread.sleep(2000);
        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(23));
        Thread.sleep(2000);
        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(23));
        Thread.sleep(2000);
        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(23));
        Thread.sleep(2000);
        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(23));
        Thread.sleep(2000);

        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(4));
        Thread.sleep(2000);

        WebElement overview_panel = (WebElement) wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("com.example.android.tvleanback:id/details_overview_actions")));
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        if (overview_panel.isDisplayed())
            jse.executeScript("browserstack_executor: {\"action\":\"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test Passed\"}}");
        else
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Test Failed\"}}");


    }
    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
