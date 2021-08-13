package pageObjects.nativePageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import pageObjects.PageObject;

@Getter
public class BudgetActivityPage extends PageObject {
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.FrameLayout[2]//android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Budget']")
    private MobileElement pageTitleLabel;

    private BudgetActivityPage(AppiumDriver driver) {
        super(driver);
    }

    public static BudgetActivityPage using(AppiumDriver driver) {
        return new BudgetActivityPage(driver);
    }

    public String getTitle() {
        return pageTitleLabel.getText();
    }
}
