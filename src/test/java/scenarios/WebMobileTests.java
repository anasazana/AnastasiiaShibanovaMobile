package scenarios;

import static pageObjects.webPageObjects.GoogleSearchPage.GOOGLE_URL;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.webPageObjects.GoogleSearchPage;
import setup.BaseTest;

public class WebMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void simpleWebTest() {
        getDriver().get("http://iana.org"); // open IANA homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        // Check IANA homepage title
        assert ((WebDriver) getDriver())
                .getTitle()
                .equals("Internet Assigned Numbers Authority") : "This is not IANA homepage";

        // Log that test finished
        System.out.println("Site opening done");
    }

    @DataProvider
    private Object[][] googleSearchDataProvider() {
        return new Object[][]{
                {"EPAM"}
        };
    }

    @Test(groups = {"web"},
            description = "Make sure that Google search of '{keyword}' returns relevant results",
            dataProvider = "googleSearchDataProvider")
    public void simpleGoogleSearchTest(String keyword) {
        getDriver().get(GOOGLE_URL); // open Google homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        // Perform search
        new GoogleSearchPage(getDriver()).searchFor(keyword);

        // Analyse the results
        List<String> searchResults = new GoogleSearchPage(getDriver()).getSearchResults();
        List<String> properSearchResult = searchResults
                .stream()
                .filter(s -> s.toUpperCase().contains(keyword.toUpperCase()))
                .collect(Collectors.toList());
        assert !properSearchResult.isEmpty()
                : "There should be some relevant results containing '" + keyword + "'";
    }
}
