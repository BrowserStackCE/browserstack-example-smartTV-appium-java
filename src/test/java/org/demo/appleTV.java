package org.demo;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.CommandExecutionHelper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import static io.appium.java_client.MobileCommand.pressKeyCodeCommand;

public class appleTV {
    DesiredCapabilities caps = new DesiredCapabilities();
    String username ="nitishbhardwaj_5gA7SZ";;
    public String accessKey =  "pA7Scg2dhesPdfqHpLe9";
    private static final String BROWSERSTACK_HUB_URL = "hub-cloud.browserstack.com";
    public AppiumDriver driver;

    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {

        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
        bstackOptions.putIfAbsent("source", "testng-java:sample-sdk:v1.0");
        capabilities.setCapability("bstack:options", bstackOptions);
        driver = new AppiumDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);
    }
    @Test
    public void appleTVTest() throws Exception {

        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        System.out.println(driver.getPageSource());
        driver.getScreenshotAs(OutputType.FILE);
        boolean status=driver.getScreenshotAs(OutputType.FILE).isFile();
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        if (status)
            jse.executeScript("browserstack_executor: {\"action\":\"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test Passed\"}}");
        else
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Test Failed\"}}");



    }
    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
