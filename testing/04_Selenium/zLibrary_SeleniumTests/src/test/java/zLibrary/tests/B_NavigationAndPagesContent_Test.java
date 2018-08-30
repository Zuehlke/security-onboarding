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
import zLibrary.loggedAdminPages.LibraryEditorPage;
import zLibrary.loggedAdminPages.RentedOverviewPage;
import zLibrary.loggedAdminPages.UserManagerPage;
import zLibrary.loggedUserPages.AboutPage;
import zLibrary.loggedUserPages.LibraryPage;
import zLibrary.loggedUserPages.MyRentedItemsPage;
import zLibrary.loggedUserPages.OrderBookPage;
import zLibrary.settings.BrowserFactory;
import zLibrary.PageConstants;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class B_NavigationAndPagesContent_Test implements PageConstants {

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
    //@Description("Initialises page object classes | Initialises WebDriver | Logs in using Cookie (Temporary solution) | Sets implicit wait object")
    static void setup() {
        driver = BrowserFactory.startBrowser(BROWSER, TESTING_URL);
        loginScreen = PageFactory.initElements(driver, LoginPage.class);
        navigation = PageFactory.initElements(driver, Navigation.class);
        library =  PageFactory.initElements(driver, LibraryPage.class);
        rentedItems = PageFactory.initElements(driver, MyRentedItemsPage.class);
        orderBook = PageFactory.initElements(driver, OrderBookPage.class);
        about = PageFactory.initElements(driver, AboutPage.class);
        libraryEditorP = PageFactory.initElements(driver, LibraryEditorPage.class);
        rentedOverviewP = PageFactory.initElements(driver, RentedOverviewPage.class);
        userManagement = PageFactory.initElements(driver, UserManagerPage.class);
        expliciteWait = new WebDriverWait(driver, 5);
        loginWithCookie();
    }

    @Test
    //@Description("PRECONDITION: Logged in as ADMIN | VERIFICATIONS: Library section elements are displayed - D_Search field, > 100 books loaded | POSTCONDITION: null")
    void a_librarySection() {
        expliciteWait.until(ExpectedConditions.visibilityOf(navigation.getLibLogo()));
        navigation.clickLibrary();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(library.getAvailableBooksButtonLocator()));
        assertTrue(library.isDisplayedSearchField(), "D_Search field is not displayed.");
        List<String> books = library.listBookNames();
        assertTrue(books.size() > 100, "Not all books are loaded.");
    }

    @Test
    //@Description("PRECONDITION: Logged in as ADMIN | VERIFICATIONS: My rented items section elements are displayed - Panel title, Rented books list (If any), If no rented books info text | POSTCONDITION: null")
    void b_myRentedItemsSection() {
        //loginWithCookie();
        expliciteWait.until(ExpectedConditions.visibilityOf(navigation.getMyRentedItemsElement()));
        navigation.clickMyRentedItems();
        //expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rentedItems.getReturnButtonsLocator()));
        assertTrue(rentedItems.isDisplayedPanelTitle(), "Panel title is not displayed,");
        List<WebElement> rentedBooksReturnButtons = rentedItems.getListOfReturnButtons();
        if(rentedBooksReturnButtons.size() == 0) assertTrue(rentedItems.isDisplayedNoBooksText(), "\"No books\" text is not displayed for this user.");
        else assertTrue(true, "Rented books are displayed.");

    }

    @Test
    //@Description("PRECONDITION: Logged in as ADMIN | VERIFICATIONS: Order book section elements are displayed - Panel title, Radio buttons, Input fields | POSTCONDITION: null")
    void c_orderBookSection() {
        //loginWithCookie();
        expliciteWait.until(ExpectedConditions.visibilityOf(navigation.getOrderBookLinkElement()));
        navigation.clickOrderBook();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderBook.getRadioButtonsLocator()));
        assertTrue(orderBook.isDisplayedPanelTitle(), "Panel title is not displayed.");
        assertTrue(orderBook.areDisplayedRadioButtons(), "Three radio buttons are not displayed.");
        assertTrue(orderBook.isDisplayedInputTitle(), "Title input field is not displayed.");
        assertTrue(orderBook.isDisplayedInputAuthor(), "Author input field is not displayed.");
        assertTrue(orderBook.isDisplayedInputURL(),"URL input field is not displayed.");
        assertTrue(orderBook.isDisplayedOrderButton(), "Order button is not displayed.");
        assertTrue(orderBook.isDisplayedResetFieldsButton(), "Reset fields button is not displayed.");
    }

    @Test
    //@Description("PRECONDITION: Logged in as ADMIN | VERIFICATIONS: About section elements are displayed - New books section, Developer section | POSTCONDITION: null")
    void d_aboutSection() {
        //loginWithCookie();
        expliciteWait.until(ExpectedConditions.visibilityOf(navigation.getLibLogo()));
        navigation.clickAbout();
        //assertTrue(about.isPresentTitle(), "Title is not present in code!");
        expliciteWait.until(ExpectedConditions.visibilityOf(about.getTitle()));
        assertTrue(about.isDisplayedNewBooks(), "New books section is not displayed.");
        assertTrue(about.isDisplayedDev(), "Contact developers team section is not displayed.");
    }

    @Test
    //@Description("PRECONDITION: Logged in as ADMIN | VERIFICATIONS: Library editor section elements are displayed - Input fields, List of books, Buttons, Tabs | POSTCONDITION: null")
    void e_libraryEditor() {
        //loginWithCookie();
        expliciteWait.until(ExpectedConditions.visibilityOf(navigation.getLibraryEditorElement()));
        navigation.clickLibraryEditor();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(libraryEditorP.getBookDeleteButtonsLocator()));
        assertTrue(libraryEditorP.isDisplayedCreatePublicationTab(), "Create publication tab is not displayed.");
        assertTrue(libraryEditorP.isDisplayedAddBookTab(), "Add book tab is not displayed");
        assertTrue(libraryEditorP.isDisplayedUploadFileButton(), "Upload cover image button is not displayed.");
        assertTrue(libraryEditorP.isDisplayedTitleInputField(), "Title input field is not displayed.");
        assertTrue(libraryEditorP.isDisplayedAuthorInputField(), "Author input field is not displayed.");
        assertTrue(libraryEditorP.isDisplayedISDNInputField(), "ISBN input field is not displayed.");
        assertTrue(libraryEditorP.isDisplayedDateInputField(), "Date input field is not present.");
        assertTrue(libraryEditorP.isDisplayedCreatePublicationButton(), "Create publication button is not displayed.");
        assertTrue(libraryEditorP.isDisplayedRemoveBooksSubtitle(), "Remove books subtitle is not displayed.");
        assertTrue(libraryEditorP.isDisplayedSearchfield(), "D_Search field is not displayed.");

        List<WebElement> books = libraryEditorP.getListOfBooks();

        assertTrue(books.get(0).isDisplayed(), "Books list is not displayed.");
        assertTrue(books.size() > 100, "Not all books are loaded.");
    }

    @Test
    //@Description("PRECONDITION: Logged in as ADMIN | VERIFICATIONS: Rented overview section elements are displayed - D_Search field, Panel title, Books listed | POSTCONDITION: null")
    void f_rentedOverview() {
        //loginWithCookie();
        //expliciteWait.until(ExpectedConditions.visibilityOf(navigation.getRentedOverviewLinkElement()));
        navigation.clickRentedOverview();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rentedOverviewP.getBookReturnButtonsLocator()));
        assertTrue(rentedOverviewP.isDisplayedPanelTitle(), "Rented books overview panel title is not displayed.");
        assertTrue(rentedOverviewP.isDisplayedSearchField(), "D_Search field is not displayed.");

        List<WebElement> bookTitles = rentedOverviewP.getBooksTitles();
        List<WebElement> bookAuthors = rentedOverviewP.getBooksAuthors();
        List<WebElement> bookRentees = rentedOverviewP.getBooksRentees();
        List<WebElement> bookReturnButtons = rentedOverviewP.getBooksReturnButtons();

        int numBookTitles = bookTitles.size();
        boolean verifyTableStructure = (numBookTitles == bookAuthors.size()) && (numBookTitles == bookRentees.size()) && (numBookTitles == bookReturnButtons.size());

        assertTrue(verifyTableStructure, "Number of scanned elements is not good, check XPaths!");

        assertTrue(bookTitles.get(0).isDisplayed(), "Book title is not displayed.");
        assertTrue(bookAuthors.get(0).isDisplayed(), "Book author is not displayed.");
        assertTrue(bookRentees.get(0).isDisplayed(), "Book rentee is not displayed.");
        assertTrue(bookReturnButtons.get(0).isDisplayed(), "Book return button is not displayed.");
    }

    @Test
    //@Description("PRECONDITION: Logged in as ADMIN | VERIFICATIONS: User manager section elements are displayed - Users listed with corresponding elements | POSTCONDITION: null")
    void g_userManager() {
        //loginWithCookie();
        expliciteWait.until(ExpectedConditions.visibilityOf(navigation.getUserManagerElement()));
        navigation.clickUserManager();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(userManagement.getUserToggleButtonLocator()));
        assertTrue(userManagement.isDisplayedPanelTitle(), "Panel title is not displayed.");

        List<WebElement> userUsernames = userManagement.getUserUsernames();
        List<WebElement> userEmails = userManagement.getUserEmail();
        List<WebElement> userRoles = userManagement.getUserRole();
        List<WebElement> userToggleButtons = userManagement.getUserToggleButton();

        int numUserUsernames = userUsernames.size();
        boolean verifyTableStructure = (numUserUsernames == userEmails.size()) && (numUserUsernames == userRoles.size()) && (numUserUsernames == userToggleButtons.size());

        assertTrue(verifyTableStructure, "Number of scanned elements is not good, check XPaths!");

        assertTrue(userUsernames.get(0).isDisplayed(), "User username is not displayed.");
        assertTrue(userEmails.get(0).isDisplayed(), "User email is not displayed.");
        assertTrue(userRoles.get(0).isDisplayed(), "User role is not displayed.");
        assertTrue(userToggleButtons.get(0).isDisplayed(), "User toggle button is not displayed.");

    }

    @AfterClass
    //@Description("Logs out, closes browser")
    static void logout() {
        navigation.clickLogout();
        driver.quit();
    }

    private static void loginWithCookie() {
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




    private void login(String userType) {
        String user = userType.toLowerCase();
        if(user.contains("user")) {
            // Perform user login
        } else {
            // perform admin login
        }
    }

}
