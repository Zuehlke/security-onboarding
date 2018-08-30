package zLibrary.loggedUserPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class LibraryPage {

    @FindBy(how = How.XPATH, using = "//h2[text()='Books in zLibrary')]")
    List<WebElement> pageTitle;

    @FindBy(how = How.CSS, using = "input[placeholder='Type a word and press enter/space']")
    List<WebElement> searchField;

    @FindBy(how = How.CSS, using = "span[class='label panel-heading-bookTitle ng-binding']")
    List<WebElement> bookTitles;

    @FindBy(how = How.CSS, using = "small[class='panel-heading-author ng-binding']")
    List<WebElement> bookAuthors;

    @FindBy(how = How.CSS, using = "div[class='isbn-text ng-binding']")
    List<WebElement> bookISBN;

    @FindBy(how = How.CSS, using = "a[class='btn btn-default zlib-button-size']")
    List<WebElement> bookShare;

    @FindBy(how = How.CSS, using = "button[class='btn btn-default zlib-button zlib-button-size']")
    List<WebElement> bookComments;

    @FindBy(how = How.CSS, using = "button[ng-click='reserveBook(book)']")
    List<WebElement> bookReserveButton;

    By bookReserveButtonLocator = By.cssSelector("button[ng-click='reserveBook(book)']");

    @FindBy(how = How.CSS, using = "button[ng-click='returnBook(book)']")
    List<WebElement> bookReturnButton;

    @FindBy(how = How.XPATH, using = "//div[text()='ISBN: 978-0131479418']/following-sibling::div[1]/div[2]/button")
    List<WebElement> agileTestingBookButton;

//    By pageTitle = By.xpath("//h2[text()='Books in zLibrary')]");
//    By searchField = By.xpath("//input[@placeholder='Type a word and press enter/space']");
//    By bookTitles = By.xpath("//span[@class='label panel-heading-bookTitle ng-binding']");
//    By bookAuthors = By.xpath("//small[@class='panel-heading-author ng-binding']");
//    By bookISBN = By.xpath("//div[@class='isbn-text ng-binding']");
//    By bookShare = By.xpath("//a[@class='btn btn-default zlib-button-size']");
//    By bookComments = By.xpath("//button[@class='btn btn-default zlib-button zlib-button-size']");
//    By bookReserveButton = By.xpath("//button[@ng-click='reserveBook(book)']");
//    By bookReturnButton = By.xpath("//button[@ng-click='returnBook(book)']");
//    By agileTestingBookButton = By.xpath("//div[text()='ISBN: 978-0131479418']/following-sibling::div[1]/div[2]/button");

    //Elements are ordered as they are displayed in application looking from top left page corner.
    //Most of elements have methods:
    // isDisplayed - Returns boolean if el is displayed on page
    // isPresent - If element is present in code
    // getElement - Return element itself

    //-------------------------------------------------PAGE TITLE-----------------------------------------------------//

    public boolean isPresentTitle() { return pageTitle.size() > 0; }

    public boolean isDisplayedTitle() { return pageTitle.get(0).isDisplayed(); }

    public WebElement getTitleElement() { return pageTitle.get(0); }

    //------------------------------------------------SEARCH FIELD----------------------------------------------------//

    public boolean isPresentSearchField() { return searchField.size() > 0; }

    public boolean isDisplayedSearchField() { return searchField.get(0).isDisplayed(); }


    public void searchFieldInput(String search) { searchField.get(0).sendKeys(search); }

    public WebElement getSearchFieldElement() { return searchField.get(0); }

    //-------------------------------------------------BOOKS LIST-----------------------------------------------------//

    public List<WebElement> getAvailableBooksList() { return bookReserveButton; }

    public By getAvailableBooksButtonLocator() { return bookReserveButtonLocator; }

    public List<WebElement> getMyReservedBooksList() { return bookReturnButton; }

    public List<String> listBookNames() {
        List<WebElement> books = bookTitles;
        List<String> bookNames = new ArrayList();
        for(int i = 0; i < books.size(); i++) {
            WebElement currentBook = books.get(i);
            bookNames.add(currentBook.getText());
        }
        return bookNames;
    }

    public void clickReserveBookButton() { agileTestingBookButton.get(0).click(); }

    public String getReserveBookButtonString() { return agileTestingBookButton.get(0).getText(); }














}
