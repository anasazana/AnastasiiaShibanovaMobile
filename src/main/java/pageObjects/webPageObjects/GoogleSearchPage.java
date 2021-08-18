package pageObjects.webPageObjects;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObject;
import utils.PropertyReader;

@Getter
public class GoogleSearchPage extends PageObject {
    private final AppiumDriver driver;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchField;

    @FindBy(xpath = "//div[@id='rso']/div//a/div[2]/div")
    private List<WebElement> searchResults;

    private GoogleSearchPage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public static GoogleSearchPage using(AppiumDriver driver) {
        return new GoogleSearchPage(driver);
    }

    public GoogleSearchPage launch() {
        driver.get(PropertyReader.getProperty("google.homepage"));
        return this;
    }

    public GoogleSearchPage searchFor(String keyword, String platform) {
        searchField.click();
        searchField.sendKeys(keyword + "\n");
        if (platform.equals("iOS")) {
            searchField.submit();
        }
        return this;
    }

    public List<String> getSearchResults() {
        return searchResults
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
