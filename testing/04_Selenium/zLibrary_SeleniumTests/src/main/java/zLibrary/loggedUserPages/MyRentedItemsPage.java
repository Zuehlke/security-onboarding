package zLibrary.loggedUserPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class MyRentedItemsPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//td[@title=''Book'']/div/strong")
    List<WebElement> rentedBooks;

    @FindBy(how = How.CSS, using = "button[ng-click='returnBook(item)']")
    List<WebElement> rentedBooksReturnButton;

    By rentedBooksReturnButtonLocator = By.cssSelector("button[ng-click='returnBook(item)']");

    @FindBy(how = How.XPATH, using = "//h2[text()='List of items in your possession']")
    List<WebElement> rentedBooksTitle;

    @FindBy(how = How.XPATH, using = "//p[contains(text(), 'You have no rented books')]")
    List<WebElement> noRentedBooksText;

    @FindBy(how = How.CSS, using = "h2[class='panel-title']")
    List<WebElement> rentedBooksPanelTitle;

//    By rentedBooks = By.xpath("//td[@title=''Book'']/div/strong");
//    By rentedBooksReturnButton = By.xpath("//button[@ng-click='returnBook(item)']");
//    By rentedBooksTitle = By.xpath("//h2[text()='List of items in your possession']");
//    By noRentedBooksText = By.xpath("//p[contains(text(), 'You have no rented books')]");
//    By rentedBooksPanelTitle = By.xpath("//h2[@class='panel-title']");

    //Elements are ordered as they are displayed in application looking from top left page corner.
    //Most of elements have methods:
    // isDisplayed - Returns boolean if el is displayed on page
    // isPresent - If element is present in code
    // getElement - Return element itself

    //-------------------------------------------------PAGE TITLE-----------------------------------------------------//

    public WebElement getPageTitle() { return rentedBooksTitle.get(0); }

    public boolean isPresentTitle() { return rentedBooksTitle.size() > 0; }

    public boolean isDisplayedTitle() { return rentedBooksTitle.get(0).isDisplayed(); }


    //-------------------------------------------------NO BOOKS TEXT--------------------------------------------------//

    public WebElement getNoRentedBooksText() { return noRentedBooksText.get(0); }

    public boolean isPresentNoBooksText() { return noRentedBooksText.size() > 0; }

    public boolean isDisplayedNoBooksText() { return noRentedBooksText.get(0).isDisplayed(); }

    //------------------------------------------------PANEL TITLE-----------------------------------------------------//

    public boolean isDisplayedPanelTitle() { return rentedBooksPanelTitle.get(0).isDisplayed(); }

    public boolean isPresentPanelTitle() { return rentedBooksPanelTitle.size() > 0; }

    //------------------------------------------------LIST OF BOOKS---------------------------------------------------//

    public List<String> getListOfRentedBooks() {
        List<WebElement> books = rentedBooks;
        List<String> bookNames = new ArrayList();
        for(int i = 0; i < books.size(); i++) {
            bookNames.add(books.get(i).getText());
        }
        return bookNames;
    }

    public List<WebElement> getListOfReturnButtons() { return rentedBooksReturnButton; }

    public By getReturnButtonsLocator() { return rentedBooksReturnButtonLocator; }

}
