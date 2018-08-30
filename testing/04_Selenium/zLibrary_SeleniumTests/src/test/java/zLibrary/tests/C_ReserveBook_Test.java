package zLibrary.tests;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import zLibrary.LoginPage;
import zLibrary.Navigation;
import zLibrary.loggedUserPages.LibraryPage;
import zLibrary.loggedUserPages.MyRentedItemsPage;
import zLibrary.settings.BrowserFactory;
import zLibrary.PageConstants;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class C_ReserveBook_Test implements PageConstants {

    static WebDriver driver;
    static LoginPage loginScreen;
    static Navigation navigation;
    static LibraryPage library;
    static MyRentedItemsPage rentedItems;
    static WebDriverWait expliciteWait;


    @BeforeClass
    //@Description("Initialises page object classes | Initialises WebDriver | Logs in using Cookie (Temporary solution) | Sets implicit wait object")
    static void setup() {
        driver = BrowserFactory.startBrowser(BROWSER, TESTING_URL);
        loginScreen = PageFactory.initElements(driver, LoginPage.class);
        navigation = PageFactory.initElements(driver, Navigation.class);
        library =  PageFactory.initElements(driver, LibraryPage.class);
        rentedItems = PageFactory.initElements(driver, MyRentedItemsPage.class);
        expliciteWait = new WebDriverWait(driver, 5);
    }

    @Test
    //@Description("PRECONDITION: Browser open on zlibrary page | PROCESS: 1. Reserves three books 2. Returns three books | VERIFICATION: Button status changes (On library section), Books listed in \"My rented items\", books returned")
    void reserveBook() {
        loginWithCookie();
        expliciteWait.until(ExpectedConditions.visibilityOf(navigation.getLibraryLinkElement()));
        navigation.clickLibrary();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(library.getAvailableBooksButtonLocator()));
        List<WebElement> availableBooks = library.getAvailableBooksList();
        List<WebElement> myReservedBooks = library.getMyReservedBooksList();
        int numAvailableBooks = availableBooks.size();
        int numMyReservedBooks = myReservedBooks.size();
        reserveThreeBooksVerifyReservations(availableBooks, numAvailableBooks, numMyReservedBooks);
        navigation.clickMyRentedItems();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rentedItems.getReturnButtonsLocator()));
        verifyMyRentedItemsContentAndReturnBooks(numMyReservedBooks);
        navigation.clickLibrary();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(library.getAvailableBooksButtonLocator()));

        myReservedBooks = library.getMyReservedBooksList();
        availableBooks = library.getAvailableBooksList();

        assertTrue((myReservedBooks.size() == numMyReservedBooks) && (availableBooks.size() == numAvailableBooks), "Not all returned books are listed in Library section." );

        pause(4000);

        navigation.clickLogout();
        driver.quit();
    }




    private void reserveThreeBooksVerifyReservations(List<WebElement> availableBooks, int numAvailableBooks, int numMyReservedBooks) {
        availableBooks.get(0).click();
        availableBooks.get(1).click();
        availableBooks.get(2).click();

        pause(1000);

        List<WebElement> availableBooksNew = library.getAvailableBooksList();
        List<WebElement> myReservedBooks = library.getMyReservedBooksList();

        assertTrue(numAvailableBooks == availableBooksNew.size() + 3, "Number of available books after reservations is not proper." + numAvailableBooks + " , " + availableBooksNew.size());
        assertTrue(numMyReservedBooks == myReservedBooks.size() - 3, "Number of reserved books after reservation is not proper.");
    }


    private void verifyMyRentedItemsContentAndReturnBooks(int numMyReservedBooksOld) {
        List<WebElement> rentedBooksButtons = rentedItems.getListOfReturnButtons();
        int lastBookIndex = rentedBooksButtons.size() - 1;
        assertTrue(rentedBooksButtons.size() == numMyReservedBooksOld + 3, "New reserved books are not displayed in rented books list.");
        rentedBooksButtons.get(lastBookIndex).click();
        pause(1000);
        rentedBooksButtons.get(--lastBookIndex).click();
        pause(1000);
        rentedBooksButtons.get(--lastBookIndex).click();

        pause(1000);

        rentedBooksButtons = rentedItems.getListOfReturnButtons();
        assertTrue(rentedBooksButtons.size() == numMyReservedBooksOld, "Not all books are returned.");
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


//    private void login(String userType) {
//        String user = userType.toLowerCase();
//        if(user.contains("user")) {
//            // Perform user login
//        } else {
//            // perform admin login
//        }
//    }
}
