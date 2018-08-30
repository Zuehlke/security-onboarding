package zLibrary.tests;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
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
import zLibrary.settings.BrowserFactory;
import zLibrary.PageConstants;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class D_Search implements PageConstants {

    static WebDriver driver;
    static LoginPage loginScreen;
    static Navigation navigation;
    static LibraryPage library;
    static WebDriverWait expliciteWait;

    @BeforeClass
    static void setup() {
        driver = BrowserFactory.startBrowser(BROWSER, TESTING_URL);
        loginScreen = PageFactory.initElements(driver, LoginPage.class);
        navigation = PageFactory.initElements(driver, Navigation.class);
        library =  PageFactory.initElements(driver, LibraryPage.class);
        expliciteWait = new WebDriverWait(driver, 5);
    }

    @Test
    //@Description("PRECONDITION: Browser open on zlibrary page | VERIFICATONS: One string, Two string, Three string search functionality, if all books contain search strings")
    void searchLibraryPageOneSearchString() {
        loginWithCookie();

        boolean isValidSearch1;
        boolean isValidSearch2;
        boolean isValidSearch3;
        navigation.clickLibrary();
        expliciteWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(library.getAvailableBooksButtonLocator()));
        library.searchFieldInput("testing");
        library.getSearchFieldElement().sendKeys(Keys.ENTER);
        pause(1000);
        List<String> booksFirstSearch = library.listBookNames();
        isValidSearch1 = resultsContainString(booksFirstSearch, "testing");
        assertTrue(isValidSearch1, "D_Search with one search string contains invalid result.");

        library.searchFieldInput("software");
        library.getSearchFieldElement().sendKeys(Keys.ENTER);
        pause(1000);
        booksFirstSearch = library.listBookNames();
        isValidSearch1 = resultsContainString(booksFirstSearch, "testing");
        isValidSearch2 = resultsContainString(booksFirstSearch, "software");

        assertTrue(isValidSearch1 && isValidSearch2, "D_Search with two search strings contains invalid result.");

        library.searchFieldInput("advanced");
        library.getSearchFieldElement().sendKeys(Keys.ENTER);
        pause(1000);
        booksFirstSearch = library.listBookNames();
        isValidSearch1 = resultsContainString(booksFirstSearch, "testing");
        isValidSearch2 = resultsContainString(booksFirstSearch, "software");
        isValidSearch3 = resultsContainString(booksFirstSearch, "advanced");

        assertTrue(isValidSearch1 && isValidSearch2 && isValidSearch3, "D_Search with three search strings contains invalid result.");

    }

    @AfterClass
    static void logout() {
        navigation.clickLogout();
        driver.quit();
    }



    private boolean resultsContainString(List<String> books, String str) {
        for(int i = 0; i < books.size(); i++) {
            String title = books.get(i).toLowerCase();
            if(!title.contains(str)) return false;
        }
        return true;
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
