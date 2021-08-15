package pageObjects.nativePageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import pageObjects.PageObject;

@Getter
public class RegistrationPage extends PageObject {
    @AndroidFindBy(xpath = "//TextInputLayout[@text='Email']//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Email']/following-sibling::XCUIElementTypeTextField")
    private MobileElement emailRegField;

    @AndroidFindBy(xpath = "//TextInputLayout[@text='Username']//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Username']/following-sibling::XCUIElementTypeTextField")
    private MobileElement usernameRegField;

    @AndroidFindBy(xpath = "//TextInputLayout[@text='Password']//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Password']"
            + "/following-sibling::XCUIElementTypeSecureTextField")
    private MobileElement passwordRegField;

    @AndroidFindBy(xpath = "//TextInputLayout[@text='Confirm Password']//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Confirm password']"
            + "/following-sibling::XCUIElementTypeSecureTextField")
    private MobileElement passwordConfRegField;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch")
    private MobileElement userAgreementBtn;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='REGISTER NEW ACCOUNT']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Register new account']/..")
    private MobileElement registerAccountBtn;

    private RegistrationPage(AppiumDriver driver) {
        super(driver);
    }

    public static RegistrationPage using(AppiumDriver driver) {
        return new RegistrationPage(driver);
    }

    public RegistrationPage setEmail(String email) {
        emailRegField.click();
        emailRegField.sendKeys(email);
        return this;
    }

    public RegistrationPage setUsername(String username) {
        usernameRegField.click();
        usernameRegField.sendKeys(username);
        return this;
    }

    public RegistrationPage setPassword(String pwd) {
        passwordRegField.click();
        passwordRegField.sendKeys(pwd);
        return this;
    }

    public RegistrationPage confirmPassword(String pwd) {
        passwordConfRegField.click();
        passwordConfRegField.sendKeys(pwd);
        return this;
    }

    public RegistrationPage confirmAgreement(String platform) {
        userAgreementBtn.click();
        if (platform.equals("iOS")) {
            registerAccountBtn.click();
        }
        return checkThatAgreementIsConfirmed(platform);
    }

    private RegistrationPage checkThatAgreementIsConfirmed(String platform) {
        assert (platform.equals("iOS")
                ? userAgreementBtn.getAttribute("value").equals("1")
                : userAgreementBtn.isSelected())
                : "User agreement should be confirmed.";
        return this;
    }

    public void register() {
        registerAccountBtn.click();
    }
}
