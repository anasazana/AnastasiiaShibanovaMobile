package pageObjects.nativePageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import pageObjects.PageObject;

@Getter
public class LoginPage extends PageObject {
    @AndroidFindBy(xpath = "//android.widget.Button[@text='SIGN IN']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Sign In']/..")
    private MobileElement signInBtn;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='REGISTER NEW ACCOUNT']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Register new account']/..")
    private MobileElement registerBtn;

    @AndroidFindBy(xpath = "//TextInputLayout[@text='Email or Username']//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Username or email']"
            + "/following-sibling::XCUIElementTypeTextField")
    private MobileElement loginField;

    @AndroidFindBy(xpath = "//TextInputLayout[@text='Password']//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Password']"
            + "/following-sibling::XCUIElementTypeSecureTextField")
    private MobileElement passwordField;

    private LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public static LoginPage using(AppiumDriver driver) {
        return new LoginPage(driver);
    }

    public LoginPage setEmail(String email) {
        loginField.sendKeys(email);
        return this;
    }

    public LoginPage setPassword(String pwd) {
        passwordField.sendKeys(pwd);
        return this;
    }

    public void signIn() {
        signInBtn.click();
    }

    public void clickOnRegisterBtn() {
        registerBtn.click();
    }
}
