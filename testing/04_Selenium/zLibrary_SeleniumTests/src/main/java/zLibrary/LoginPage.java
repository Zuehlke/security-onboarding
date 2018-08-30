package zLibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class LoginPage {


    @FindBy(how = How.XPATH, using = "//button[@ng-click='oauth2()']")
            private WebElement loginButton;

    @FindBy(how = How.CSS, using = "img[alt='Zuehlke library']")
            private List<WebElement> zuhlkeImage;

    @FindBy(how = How.XPATH, using = "//h1[text() = 'Welcome to Zühlke library']")
            private List<WebElement> pageTitle;



    public void clickLoginButton() { loginButton.click(); }

    public boolean isDisplayedLoginButton() { return loginButton.isDisplayed(); }

    public boolean isPresentZuhlkeImage() { return zuhlkeImage.size() == 1; }

    public boolean isDisplayedZuhlkeImage() { return zuhlkeImage.get(0).isDisplayed(); }

    public boolean isPresentTitle() { return pageTitle.size() == 1; }

    public WebElement getTitle() { return pageTitle.get(0); }

    public boolean isDisplayedTitle() { return pageTitle.get(0).isDisplayed(); }



}
