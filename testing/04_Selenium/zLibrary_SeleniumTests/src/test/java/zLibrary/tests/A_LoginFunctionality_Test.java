package zLibrary.tests;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import zLibrary.LoginPage;
import zLibrary.Navigation;
import zLibrary.loggedUserPages.LibraryPage;
import zLibrary.loggedUserPages.MyRentedItemsPage;
import zLibrary.settings.BrowserFactory;
import zLibrary.PageConstants;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

//import org.testng.annotations.BeforeTest;

//import static org.testng.Assert.assertEquals;


public class A_LoginFunctionality_Test implements PageConstants {

    static WebDriver driver;
    static LoginPage loginScreen;
    static Navigation navigation;
    static LibraryPage library;
    static MyRentedItemsPage rentedItems;
    static WebDriverWait expliciteWait;

    @BeforeClass
    static void setup() {
        driver = BrowserFactory.startBrowser(BROWSER, TESTING_URL);
        loginScreen = PageFactory.initElements(driver, LoginPage.class);
        navigation = PageFactory.initElements(driver, Navigation.class);
        library =  PageFactory.initElements(driver, LibraryPage.class);
        rentedItems = PageFactory.initElements(driver, MyRentedItemsPage.class);
        expliciteWait = new WebDriverWait(driver, 5);
    }

//    @Test
//    void loginWithUser() {
//        loginWithCookie();
//        //assertTrue(navigation.isPresentLibLogo(), "Library logo is not present in code!");
//        expliciteWait.until(ExpectedConditions.visibilityOf(navigation.getLibLogo()));
//        verifyUserLinksAreDisplayed();
//        //verifyAdminLinksAreNotDisplayed();
//        navigation.clickLogout();
//        verifyUserHasLoggedOut();
//    }

    @Test
    //@Description("PRECONDITION: ADMIN log in | VERIFY: All navigation links are present for admin | POSTCONDITION: Logs out, verifies logout is successful, closes browser")
    void loginWithAdmin() {
        loginWithCookie();
        //assertTrue(navigation.isPresentLibLogo(), "Library logo is not present in code!");
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(library.getAvailableBooksButtonLocator()));
        verifyUserLinksAreDisplayed();
        verifyAdminLinksAreDisplayed();
        navigation.clickLogout();
        verifyUserHasLoggedOut();
    }

    @AfterClass
    static void closeBrowser() {
        driver.quit();
    }

    private void pause(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    private void loginWithCookie() {
        Cookie cookie = new Cookie.Builder("currentUser", COOKIE_VALUE)
                .domain(".zlibrary.eu-west-1.elasticbeanstalk.com")
                .build();
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }


    private void verifyUserLinksAreDisplayed() {
        assertTrue(navigation.isDisplayedUsername(USER_NAME, USER_SURNAME), "Username is not properly displayed.");
        assertTrue(navigation.isDisplayedAbout(), "About link is not displayed.");
        assertTrue(navigation.isDisplayedLogout(), "Logout link is not displayed.");
        assertTrue(navigation.isDisplayedMyRentedItems(), "My rented items link is not displayed.");
        assertTrue(navigation.isDisplayedLibrary(), "My library link is not displayed.");
        assertTrue(navigation.isDisplayedMyRentedItems(), "My rented items link is not displayed.");
    }

    private void verifyAdminLinksAreDisplayed() {
        assertTrue(navigation.isDisplayedLibraryEditor(), "Library editor link is not displayed.");
        assertTrue(navigation.isDisplayedRentedOverview(), "Rented overview link is not displayed.");
        assertTrue(navigation.isDisplayedUserManager(), "User manager link is not displayed.");
    }

    private void verifyAdminLinksAreNotDisplayed() {
        assertFalse(navigation.isDisplayedLibraryEditor(), "Library editor link is not displayed.");
        assertFalse(navigation.isDisplayedRentedOverview(), "Rented overview link is not displayed.");
        assertFalse(navigation.isDisplayedUserManager(), "User manager link is not displayed.");
    }

    private void verifyUserHasLoggedOut() {
        expliciteWait.until(ExpectedConditions.visibilityOf(loginScreen.getTitle()));
        //assertTrue(loginScreen.isDisplayedZuhlkeImage(), "Zuhlke image is not displayed on login screen.");
        assertTrue(loginScreen.isDisplayedLoginButton(), "Login button is not displayed on login screen.");
    }
}
