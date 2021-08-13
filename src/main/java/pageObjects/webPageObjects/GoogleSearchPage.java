package pageObjects.webPageObjects;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObject;

@Getter
public class GoogleSearchPage extends PageObject {

    public static final String GOOGLE_URL = "http://www.google.com";

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchField;

    @FindBy(xpath = "//div[@id='rso']/div//a/div[2]/div")
    private List<WebElement> searchResults;

    public GoogleSearchPage(AppiumDriver<WebElement> appiumDriver) {
        super(appiumDriver);
    }

    public void searchFor(String keyword) {
        searchField.click();
        searchField.sendKeys(keyword + "\n");
    }

    public List<String> getSearchResults() {
        return searchResults
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

}
