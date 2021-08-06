package pageObjects.nativePageObjects;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObject;

@Getter
public class BudgetActivityPage extends PageObject {

    @FindBy(xpath = "//android.view.ViewGroup/android.widget.FrameLayout[2]//android.widget.TextView")
    WebElement pageTitleLabel;

    @FindBy(id = "add_new_expense")
    WebElement addExpenseBtn;

    @FindBy(id = "action_bar")
    WebElement actionBar;

    public BudgetActivityPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public String getTitle() {
        return pageTitleLabel.getText();
    }
}
