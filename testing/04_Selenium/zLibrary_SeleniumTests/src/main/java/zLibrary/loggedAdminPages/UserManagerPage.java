package zLibrary.loggedAdminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class UserManagerPage {

    @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'Manage accounts')]")
    List<WebElement> pageTitle;

    @FindBy(how = How.CSS, using = "h2[class='panel-title']")
    List<WebElement> panelTitle;

    @FindBy(how = How.CSS, using = "div[class='col-lg-3 col-md-3 col-sm-3 center-block text-center ng-binding']")
    List<WebElement> userUsername;

    @FindBy(how = How.CSS, using = "div[class='col-lg-4 col-md-4 col-sm-4 center-block text-center ng-binding']")
    List<WebElement> userEmail;

    @FindBy(how = How.CSS, using = "div[class='col-lg-2 col-md-2 col-sm-2 center-block text-center ng-binding']")
    List<WebElement> userRole;

    @FindBy(how = How.XPATH, using = "//div[@class=' col-lg-2 col-md-2 col-sm-2 center-block text-center zlib-userlist-item-btns']/button")
    List<WebElement> userToggleButton;

    By userToggleButtonLocator = By.xpath("//div[@class=' col-lg-2 col-md-2 col-sm-2 center-block text-center zlib-userlist-item-btns']/button");

    @FindBy(how = How.CSS, using = "div[class='modal-content']")
    List<WebElement> toggleRolePopupWindow;

    @FindBy(how = How.CSS, using = "h3[class='modal-title ng-binding']")
    List<WebElement> toggleRolePopupWindowText;

    @FindBy(how = How.XPATH, using = "//div[@class='modal-content']/div[2]/button")
    List<WebElement> toggleRolePopupWindowOkButton;

//    By pageTitle = By.xpath("//h2[contains(text(), 'Manage accounts')]");
//    By panelTitle = By.xpath("//h2[@class='panel-title']");
//    By userUsername = By.xpath("//div[@class='col-lg-3 col-md-3 col-sm-3 center-block text-center ng-binding']");
//    By userEmail = By.xpath("//div[@class='col-lg-4 col-md-4 col-sm-4 center-block text-center ng-binding']");
//    By userRole = By.xpath("//div[@class='col-lg-2 col-md-2 col-sm-2 center-block text-center ng-binding']");
//    By userToggleButton = By.xpath("//div[@class=' col-lg-2 col-md-2 col-sm-2 center-block text-center zlib-userlist-item-btns']/button");
//    By toggleRolePopupWindow = By.xpath("//div[@class='modal-content']");
//    By toggleRolePopupWindowText = By.xpath("//h3[@class='modal-title ng-binding']");
//    By toggleRolePopupWindowOkButton = By.xpath("//div[@class='modal-content']/div[2]/button");

    //Elements are ordered as they are displayed in application looking from top left page corner.
    //Most of elements have methods:
    // isDisplayed - Returns boolean if el is displayed on page
    // isPresent - If element is present in code
    // getElement - Return element itself

    //-------------------------------------------------PAGE TITLE-----------------------------------------------------//

    public WebElement getTitle() { return pageTitle.get(0); }

    public boolean isPresentTitle() { return pageTitle.size() > 0; }

    public boolean isDisplayedTitle() { return pageTitle.get(0).isDisplayed(); }

    //-------------------------------------------------PANEL TITLE----------------------------------------------------//

    public boolean isDisplayedPanelTitle() { return panelTitle.get(0).isDisplayed(); }

    //------------------------------------------------USERS LIST------------------------------------------------------//

    public List<WebElement> getUserUsernames() { return userUsername; }

    public List<WebElement> getUserEmail() { return userEmail; }

    public List<WebElement> getUserRole() { return userRole; }

    public List<WebElement> getUserToggleButton() { return userToggleButton; }

    public By getUserToggleButtonLocator() { return userToggleButtonLocator; }

    //----------------------------------------------POPUP WINDOW------------------------------------------------------//

    public WebElement getPopupWindowElement() { return toggleRolePopupWindow.get(0); }

    public String getPopupWindowText() { return toggleRolePopupWindow.get(0).getText(); }

    public void clickOkButtonOnPopupWin() { toggleRolePopupWindowOkButton.get(0).click(); }

}
