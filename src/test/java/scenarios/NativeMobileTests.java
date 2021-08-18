package scenarios;

import data.User;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.nativePageObjects.BudgetActivityPage;
import pageObjects.nativePageObjects.LoginPage;
import pageObjects.nativePageObjects.RegistrationPage;
import setup.BaseTest;

public class NativeMobileTests extends BaseTest {

    @BeforeMethod(alwaysRun = true, groups = {"native"})
    public void reset() {
        getDriver().resetApp();
    }

    @Test(groups = {"native"}, description = "This simple test just click on the Sign In button.")
    public void simpleNativeTest() {
        logger.info("simpleNativeTest started");
        logger.info("Click on Sign In button");
        LoginPage.using(getDriver())
                .signIn();
        logger.info("simpleNativeTest done");
    }

    @DataProvider
    public Object[][] simpleRegistrationDataProvider() {
        return new Object[][]{
                {User.getRandomUser()}
        };
    }

    @Test(groups = {"native"}, description = "This test checks that user can register a new account with valid data.",
            dataProvider = "simpleRegistrationDataProvider")
    public void simpleRegistrationTest(User testUser) {
        logger.info("simpleRegistrationTest started");
        logger.info("Go to registration page");
        LoginPage.using(getDriver())
                .clickOnRegisterBtn();
        logger.info("Fill in the form");
        RegistrationPage.using(getDriver())
                .setEmail(testUser.getEmail())
                .setUsername(testUser.getUsername())
                .setPassword(testUser.getPassword())
                .confirmPassword(testUser.getPassword())
                .confirmAgreement(getPlatformName())
                .register();
        try {
            logger.info("Log in as default user");
            LoginPage.using(getDriver())
                    .setEmail(testUser.getEmail())
                    .setPassword(testUser.getPassword())
                    .signIn();
        } catch (Exception e) {
            throw new NoSuchElementException("This is not Login page.");
        }
        logger.info("Check that Budget Activity page is opened");
        assert BudgetActivityPage.using(getDriver())
                .getTitle()
                .contains("Budget") : "Budget Activity page should be opened";
        logger.info("simpleRegistrationTest done");
    }
}
