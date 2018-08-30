package zLibrary.tests;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import zLibrary.LoginPage;
import zLibrary.Navigation;
import zLibrary.PageConstants;
import zLibrary.loggedAdminPages.LibraryEditorPage;
import zLibrary.loggedAdminPages.RentedOverviewPage;
import zLibrary.loggedAdminPages.UserManagerPage;
import zLibrary.loggedUserPages.AboutPage;
import zLibrary.loggedUserPages.LibraryPage;
import zLibrary.loggedUserPages.MyRentedItemsPage;
import zLibrary.loggedUserPages.OrderBookPage;
import zLibrary.settings.BrowserFactory;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class F_ToggleUserRole implements PageConstants {

    static WebDriver driver;
    static LoginPage loginScreen;
    static Navigation navigation;
    static LibraryPage library;
    static MyRentedItemsPage rentedItems;
    static OrderBookPage orderBook;
    static AboutPage about;
    static LibraryEditorPage libraryEditorP;
    static RentedOverviewPage rentedOverviewP;
    static UserManagerPage userManagement;
    static WebDriverWait expliciteWait;

    @BeforeClass
    static void setup() {
        driver = BrowserFactory.startBrowser(BROWSER
                , TESTING_URL);
        loginScreen = PageFactory.initElements(driver, LoginPage.class);
        navigation = PageFactory.initElements(driver, Navigation.class);
        userManagement = PageFactory.initElements(driver, UserManagerPage.class);
        expliciteWait = new WebDriverWait(driver, 5);
    }


    @Test
    void changeUserRole() {
        loginWithCookie();
        expliciteWait.until(ExpectedConditions.visibilityOf(navigation.getUserManagerElement()));
        navigation.clickUserManager();
        expliciteWait.until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(userManagement.getUserToggleButtonLocator()));
        toggleRoleVerifyChange();
    }


    private void toggleRoleVerifyChange() {
        List<WebElement> userRoles = userManagement.getUserRole();
        List<WebElement> userToggleButtons = userManagement.getUserToggleButton();
        String oldRole = userRoles.get(0).getText();
        userToggleButtons.get(0).click();
        expliciteWait.until(ExpectedConditions.visibilityOf(userManagement.getPopupWindowElement()));
        String popupText = userManagement.getPopupWindowText();
        assertTrue(popupText.contains("Successfully changed role for user:"),
                "Invalid text inside popup window.");
        pause(500);
        userManagement.clickOkButtonOnPopupWin();
        String newRole = userRoles.get(0).getText();

        if(oldRole.equalsIgnoreCase("user")) {
            assertTrue(newRole.equalsIgnoreCase("admin"),
                    "User role was not successfully changed.");
        } else {
            assertTrue(newRole.equalsIgnoreCase("user"),
                    "User role was not successfully changed.");
        }

        //Return to old user role!

        pause(500);
        userToggleButtons.get(0).click();
        pause(500);
        userManagement.clickOkButtonOnPopupWin();
        pause(500);
    }





    @AfterClass
    static void logout() {
        navigation.clickLogout();
        driver.quit();
    }


    private void loginWithCookie() {
        Cookie cookie = new Cookie.Builder("currentUser", COOKIE_VALUE)
                .domain(".zlibrary.eu-west-1.elasticbeanstalk.com")
                .build();
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    private void pause(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
