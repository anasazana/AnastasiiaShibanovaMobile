package pageObjects.nativePageObjects;

import data.User;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObject;

@Getter
public class LoginPage extends PageObject {

    @FindBy(id = "email_sign_in_button")
    WebElement signInBtn;

    @FindBy(id = "register_button")
    WebElement registerBtn;

    @FindBy(id = "login_email")
    WebElement loginField;

    @FindBy(id = "login_pwd")
    WebElement passwordField;

    public LoginPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void loginAs(User user) {
        loginField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        signInBtn.click();
    }
}
