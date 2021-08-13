package pageObjects.nativePageObjects;

import data.User;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObject;

@Getter
public class RegistrationPage extends PageObject {

    @FindBy(id = "registration_email")
    WebElement emailRegField;

    @FindBy(id = "registration_username")
    WebElement usernameRegField;

    @FindBy(id = "registration_password")
    WebElement passwordRegField;

    @FindBy(id = "registration_confirm_password")
    WebElement passwordConfRegField;

    @FindBy(xpath = "//android.view.ViewGroup/android.widget.FrameLayout[2]//android.widget.CheckedTextView")
    WebElement userAgreementCheckbox;

    @FindBy(id = "register_new_account_button")
    WebElement registerAccountBtn;

    @FindBy(id = "registration_cancel_button")
    WebElement cancelBtn;

    public RegistrationPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void registerAs(User user) {
        emailRegField.sendKeys(user.getEmail());
        usernameRegField.sendKeys(user.getUsername());
        passwordRegField.sendKeys(user.getPassword());
        passwordConfRegField.sendKeys(user.getPassword());
        userAgreementCheckbox.click();
    }
}
