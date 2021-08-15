package setup;

import static java.lang.String.format;

import io.appium.java_client.AppiumDriver;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import utils.PropertyReader;

public class BaseTest implements IDriver {
    private static AppiumDriver driver;
    private static String platformName;

    protected Logger logger = LogManager.getLogger(getClass());

    @Override
    public AppiumDriver getDriver() {
        return driver;
    }

    public String getPlatformName() {
        return platformName;
    }

    @Parameters(value = {"appType", "platformName", "udid", "deviceName", "browserName",
            "app", "appPackage", "appActivity", "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String appType, String platformName,
            @Optional("") String udid,
            @Optional("") String deviceName,
            @Optional("") String browserName,
            @Optional("") String app,
            @Optional("") String appPackage,
            @Optional("") String appActivity,
            @Optional("") String bundleId
    ) {
        BaseTest.platformName = platformName;
        logger.info("Before: app type - " + appType);
        setAppiumDriver(
                appType,
                platformName,
                udid,
                deviceName,
                browserName,
                app,
                appPackage,
                appActivity,
                bundleId
        );
    }

    @AfterSuite(alwaysRun = true, groups = {"native"})
    public void tearDown() {
        driver.closeApp();
    }

    private void setAppiumDriver(
            String appType,
            String platformName,
            String udid,
            String deviceName,
            String browserName,
            String app,
            String appPackage,
            String appActivity,
            String bundleId
    ) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", platformName);

        String customUdid = System.getProperty("udid");
        if (customUdid != null) {
            capabilities.setCapability("udid", customUdid);
        } else {
            capabilities.setCapability("deviceName", deviceName);
            capabilities.setCapability("udid", udid);
        }

        if (appType.equals("web")) {
            capabilities.setCapability("browserName", browserName);
            if (browserName.contains("chrome")) {
                capabilities.setCapability("chromedriverDisableBuildCheck", "true");
            }
        } else {
            capabilities.setCapability("app", app);
            capabilities.setCapability("appPackage", appPackage);
            capabilities.setCapability("appActivity", appActivity);
            capabilities.setCapability("bundleId", bundleId);
        }

        try {
            String projectName = System.getProperty("project.name");
            String appiumHub = System.getProperty("appium.hub");
            String token = URLEncoder.encode(PropertyReader.getProperty("api.key"), StandardCharsets.UTF_8.name());
            driver = new AppiumDriver<>(
                    new URL(format("https://%s:%s@%s/wd/hub", projectName, token, appiumHub)), capabilities);
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Timeouts tuning
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
}
