package scenarios;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.webPageObjects.GoogleSearchPage;
import setup.BaseTest;
import utils.PropertyReader;

public class WebMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void simpleWebTest() {
        logger.info("simpleWebTest started");
        logger.info("Open IANA homepage");
        getDriver().get(PropertyReader.getProperty("iana.homepage"));
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
        logger.info("Check IANA homepage title");
        assert ((WebDriver) getDriver())
                .getTitle()
                .equals(PropertyReader.getProperty("iana.homepage.title")) : "This is not IANA homepage";
        logger.info("simpleWebTest done");
    }

    @Test(groups = {"web"}, description = "Make sure that Google search of '{keyword}' returns relevant results")
    public void simpleGoogleSearchTest() {
        String keyword = PropertyReader.getProperty("google.search.keyword");
        logger.info("simpleGoogleSearchTest started. Keyword: '" + keyword + "'");
        logger.info("Go to Google homepage and search for '" + keyword + "'");
        List<String> searchResults = GoogleSearchPage.using(getDriver())
                        .launch()
                        .searchFor(keyword, getPlatformName())
                        .getSearchResults();
        logger.info("Analyse search results");
        List<String> properSearchResults = searchResults
                        .stream()
                        .filter(s -> s.toUpperCase().contains(keyword.toUpperCase()))
                        .collect(Collectors.toList());
        logger.info("Check that there are some relevant results");
        assert !properSearchResults.isEmpty()
                : "There should be some relevant results containing '" + keyword + "'";
        logger.info(properSearchResults.size() + " results have been found for '" + keyword + "' keyword");
        logger.info("simpleGoogleSearchTest done");
    }
}
