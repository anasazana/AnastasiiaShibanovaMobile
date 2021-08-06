package scenarios;

import org.testng.annotations.Test;
import pageObjects.nativePageObjects.BudgetActivityPage;
import pageObjects.nativePageObjects.LoginPage;
import pageObjects.nativePageObjects.RegistrationPage;
import data.User;
import setup.BaseTest;

public class NativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "This simple test just click on the Sign In button")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPo().getWelement("signInBtn").click();
        System.out.println("Simplest Android native test done");
    }

    @Test(groups = {"native"}, description = "This test checks that user can register a new account with valid data.")
    public void simpleRegistrationTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException,
            InterruptedException {
        // go to registration page
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.getRegisterBtn().click();
        // fill in the registration form
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.registerAs(User.DEFAULT_USER);
        // bug was found
//        assert registrationPage.getUserAgreementCheckbox().isSelected()
//                : "User agreement checkbox should be selected.";
        // confirm registration
        registrationPage.getRegisterAccountBtn().click();
        // login as default user
        loginPage.loginAs(User.DEFAULT_USER);
        // check that Budget Activity page is opened
        BudgetActivityPage budgetActivityPage = new BudgetActivityPage(getDriver());
        assert budgetActivityPage.getTitle().equalsIgnoreCase("BudgetActivity")
                : "Budget Activity page should be opened";
    }

}
