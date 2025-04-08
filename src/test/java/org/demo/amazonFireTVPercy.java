package org.demo;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.CommandExecutionHelper;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.appium.java_client.MobileCommand.pressKeyCodeCommand;

public class amazonFireTVPercy {

    public AndroidDriver driver;
    private static final String SCREENSHOT_PATH = "resources/Amazon_Fire_TV/";

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
        bstackOptions.put("source", "testng-java:sample-sdk:v1.0");
        capabilities.setCapability("bstack:options", bstackOptions);
        driver = new AndroidDriver(new URL("https://hub.browserstack.com/wd/hub"), capabilities);
    }

    public void captureScreenshot(String filename) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(SCREENSHOT_PATH + filename + ".png");
        destFile.getParentFile().mkdirs(); // Ensure directory exists
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickAndCapture(WebElement element, String filename) {
        element.click();
        captureScreenshot(filename);
    }

    @Test
    public void testt() throws Exception {
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement next_button = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.View[@content-desc=\"Next\"]")));
        clickAndCapture(next_button, "next_click_1");
        Thread.sleep(5000);
        clickAndCapture(next_button, "next_click_2");
        Thread.sleep(5000);
        clickAndCapture(next_button, "next_click_3");

        WebElement get_started_button = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("com.example.android.tvleanback:id/button_start")));
        clickAndCapture(get_started_button, "get_started_click");

        Thread.sleep(2000);
        WebElement menu_sideitems = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("com.example.android.tvleanback:id/header_label")));
        clickAndCapture(menu_sideitems, "menu_sideitems_click");

        Thread.sleep(2000);
        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(22));
        captureScreenshot("key_press_22");

        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(23));
        captureScreenshot("key_press_23");

        Thread.sleep(2000);
        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(23));
        captureScreenshot("key_press_23_2");

        Thread.sleep(2000);
        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(23));
        captureScreenshot("key_press_23_3");

        Thread.sleep(2000);
        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(23));
        captureScreenshot("key_press_23_4");

        Thread.sleep(2000);
        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(23));
        captureScreenshot("key_press_23_5");

        Thread.sleep(2000);
        CommandExecutionHelper.execute(driver, pressKeyCodeCommand(4));
        captureScreenshot("key_press_4");
        uploadScreenshotsToPercy();
        Thread.sleep(2000);
//        WebElement overview_panel = wait.until(
//                ExpectedConditions.elementToBeClickable(AppiumBy.id("com.example.android.tvleanback:id/details_overview_actions")));
//
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        if (overview_panel.isDisplayed()) {
//            jse.executeScript("browserstack_executor: {\"action\":\"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test Passed\"}}");
//        } else {
//            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Test Failed\"}}");
       // }
    }
    public void uploadScreenshotsToPercy() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("node", "uploadScreenshots.js");
            processBuilder.directory(new File(System.getProperty("user.dir"))); // Set working directory
            processBuilder.inheritIO(); // Inherit I/O for debugging
            Process process = processBuilder.start();
            int exitCode = process.waitFor(); // Wait for process to complete
            if (exitCode == 0) {
                System.out.println("Screenshot upload to Percy completed successfully.");
            } else {
                System.err.println("Screenshot upload to Percy failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();

    }
}
