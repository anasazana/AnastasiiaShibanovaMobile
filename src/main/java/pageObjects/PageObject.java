package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObjects.nativePageObjects.LoginPage;
import pageObjects.webPageObjects.GoogleSearchPage;
import setup.IPageObject;

import java.lang.reflect.Field;

public class PageObject implements IPageObject {

    private Object somePageObject; // it should be set of web page or EPAM Test App WebElements

    public PageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }

    public PageObject(String appType, AppiumDriver appiumDriver) throws Exception {

        System.out.println("Current app type: " + appType);
        switch (appType) {
            case "web":
                somePageObject = new GoogleSearchPage(appiumDriver);
                break;
            case "native":
                somePageObject = new LoginPage(appiumDriver);
                break;
            default:
                throw new Exception("Can't create a page object for " + appType);
        }
    }

    @Override
    public WebElement getWelement(String weName) throws NoSuchFieldException, IllegalAccessException {
        // use reflection technique
        Field field = somePageObject.getClass().getDeclaredField(weName);
        field.setAccessible(true);
        return (WebElement) field.get(somePageObject);

    }
}
