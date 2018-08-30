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

public class E_AddAndDeleteBook implements PageConstants {

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
        driver = BrowserFactory.startBrowser(BROWSER, TESTING_URL);
        loginScreen = PageFactory.initElements(driver, LoginPage.class);
        navigation = PageFactory.initElements(driver, Navigation.class);
        library =  PageFactory.initElements(driver, LibraryPage.class);
        libraryEditorP = PageFactory.initElements(driver, LibraryEditorPage.class);
        expliciteWait = new WebDriverWait(driver, 5);
    }

    @Test
    void addNewBook() {
        loginWithCookie();
        navigation.clickLibraryEditor();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(libraryEditorP.getBookDeleteButtonsLocator()));
        libraryEditorP.clickAddBookTab();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(libraryEditorP.getSelectedPublicationFieldsLocator()));
        verifyNoPublicationIsSelected();
        libraryEditorP.enterPublicationName("TestTitle");
        verifyPublicationIsSelected();
        chooseBookType("PaperBook");
        libraryEditorP.clickAddBookButton();
        verifyBookIsListedInLibrary();
        verifyBookIsListedInLibraryEditor();
        navigation.clickLibraryEditor();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(libraryEditorP.getBookDeleteButtonsLocator()));
    }

    @Test
    void deleteAddedBook() {
        deleteBookVerifyDeletion();
        pause(6000);
    }

    @AfterClass
    static void logout() {
        navigation.clickLogout();
        driver.quit();
    }


    private void verifyNoPublicationIsSelected() {
        List<WebElement> fields = libraryEditorP.getSelectedPublicationFields();
        assertTrue(fields.get(0).getText().equals("Title:"), "Title is not empty.");
        assertTrue(fields.get(1).getText().equals("ISBN:"), "ISBN is not empty");
        assertTrue(fields.get(2).getText().equals("Author:"), "Author is not empty");
        assertTrue(fields.get(3).getText().equals("Year:"), "Year is not empty");
    }


    private void verifyPublicationIsSelected() {
        List<WebElement> fields = libraryEditorP.getSelectedPublicationFields();
        assertTrue(fields.get(0).getText().equals("Title: TestTitle"), "Title is not correct.");
        assertTrue(fields.get(1).getText().equals("ISBN: TestISBN"), "ISBN is not correct");
        assertTrue(fields.get(2).getText().equals("Author: TestAuthor"), "Author is not correct");
        assertTrue(fields.get(3).getText().equals("Year: 2018"), "Year is not correct");
    }


    private void chooseBookType(String bookType) {
        List<WebElement> bookTypeRadioButtons = libraryEditorP.getSelectPublicationRadioButtons();
        if(bookType.equalsIgnoreCase("paperbook")) bookTypeRadioButtons.get(0).click();
        else bookTypeRadioButtons.get(1).click();
    }



    private void verifyBookIsListedInLibrary() {
        navigation.clickLibrary();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(library.getAvailableBooksButtonLocator()));
        List<String> bookNames = library.listBookNames();
        boolean isPresent = false;
        for(int i = 0; i < bookNames.size(); i++) {
            if(bookNames.get(i).equals("TestTitle")) isPresent = true;
        }
        assertTrue(isPresent, "Added book is not present in library");
    }


    private void verifyBookIsListedInLibraryEditor() {
        navigation.clickLibraryEditor();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(libraryEditorP.getBookDeleteButtonsLocator()));
        List<WebElement> books = libraryEditorP.getListOfBooks();
        int numBooks = books.size();
        String lastBookName = books.get(numBooks - 1).getText();
        assertTrue(lastBookName.equals("TestTitle"));
    }


    private void deleteBookVerifyDeletion() {
        List<WebElement> books = libraryEditorP.getListOfBooks();
        List<WebElement> booksDeleteButtons = libraryEditorP.getDeleteBookButtons();
        int numBooks = books.size();
        String lastBookName = books.get(numBooks - 1).getText();
        if(lastBookName.equals("TestTitle")) booksDeleteButtons.get(numBooks - 1).click();
        else assertTrue(true, "Invalid book last listed!");
        pause(500);
        books = libraryEditorP.getListOfBooks();
        assertTrue(books.size() == numBooks - 1, "Books is not successfully deleted.");
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
