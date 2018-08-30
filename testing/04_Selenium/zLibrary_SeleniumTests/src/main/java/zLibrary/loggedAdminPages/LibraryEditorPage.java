package zLibrary.loggedAdminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class LibraryEditorPage {

    @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'Edit library')]")
    List<WebElement> pageTitle;

    @FindBy(how = How.CSS, using = "a[data-target='#publicationTabId']")
    List<WebElement> createPublicationTab;

    @FindBy(how = How.CSS, using = "a[data-target='#bookTabId']")
    List<WebElement> addBookTab;

    @FindBy(how = How.XPATH, using = "//h3[text()='Create new publication']")
    List<WebElement> createNewPublicationPanelTitle;

    @FindBy(how = How.CSS, using = "input[type='file']")
    List<WebElement> uploadBookCoverButton;

    @FindBy(how = How.CSS, using = "input[id='newBookPubTitleInput']")
    List<WebElement> titleInputField;

    @FindBy(how = How.CSS, using = "input[id='newBookPubAuthorInput']")
    List<WebElement> authorInputField;

    @FindBy(how = How.CSS, using = "input[id='newBookPubISBNInput']")
    List<WebElement> ISBNInputField;

    @FindBy(how = How.CSS, using = "input[id='newBookPubDateInput']")
    List<WebElement> publichDateInputField;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Create publication')]")
    List<WebElement> createPublicationButton;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Remove books from library:')]")
    List<WebElement> removeBooksSubtitle;

    @FindBy(how = How.CSS, using = "input[placeholder='Search']")
    List<WebElement> searchInputField;

    @FindBy(how = How.CSS, using = "button[class='btn btn-danger center-block text-center']")
    List<WebElement> deleteBookButtons;

    By deleteBookButtonsLocator = By.cssSelector("button[class='btn btn-danger center-block text-center']");

    @FindBy(how = How.CSS, using = "input[list='publicationsDataListId']")
    List<WebElement> chooseTitleDropdown;

    @FindBy(how = How.CSS, using = "datalist[id='publicationsDataListId']")
    List<WebElement> optionsDropdown;

    @FindBy(how = How.CSS, using = "option[value='TestTitle']")
    List<WebElement> testTitlePublication;

    @FindBy(how = How.XPATH, using = "//div[@class='panel panel-primary']/div[2]/p")
    List<WebElement> selectedPublicationFields;

    By selectedPublicationFieldsLocator = By.xpath("//div[@class='panel panel-primary']/div[2]/p");

    @FindBy(how = How.CSS, using = "button[ng-click='bookOperation.deleteBook(book.bookId)']")
    List<WebElement> bookDeleteButtons;

    @FindBy(how = How.CSS, using = "div[class='col-lg-4 col-md-4 col-sm-4 center-block text-center ng-binding']")
    List<WebElement> bookTitleNames;

    @FindBy(how = How.CSS, using = "input[type='radio']")
    List<WebElement> bookTypeRadioButtons;

    @FindBy(how = How.CSS, using = "button[ng-click='newBook.create()']")
    List<WebElement> addBookButton;


//    By pageTitle = By.xpath("//h2[contains(text(), 'Edit library')]");
//    By createPublicationTab = By.xpath("//a[@data-target='#publicationTabId']");
//    By addBookTab = By.xpath("//a[@data-target='#bookTabId']");
//    By createNewPublicationPanelTitle = By.xpath("//h3[text()='Create new publication']");
//    By uploadBookCoverButton = By.xpath("//input[@type='file']");
//    By titleInputField = By.xpath("//input[@id='newBookPubTitleInput']");
//    By authorInputField = By.xpath("//input[@id='newBookPubAuthorInput']");
//    By ISBNInputField = By.xpath("//input[@id='newBookPubISBNInput']");
//    By publichDateInputField = By.xpath("//input[@id='newBookPubDateInput']");
//    By createPublicationButton = By.xpath("//button[contains(text(), 'Create publication')]");
//    By removeBooksSubtitle = By.xpath("//span[contains(text(), 'Remove books from library:')]");
//    By searchInputField = By.xpath("//input[@placeholder='D_Search']");
//    By deleteBookButtons = By.xpath("//button[@class='btn btn-danger center-block text-center']");
//
//    By chooseTitleDropdown = By.xpath("//input[@list='publicationsDataListId']");
//    By optionsDropdown = By.xpath("//datalist[@id='publicationsDataListId']");
//    By testTitlePublication = By.xpath("//option[@value='TestTitle']");
//    By selectedPublicationFields = By.xpath("//div[@class='panel panel-primary']/div[2]/p");
//    By bookTitleNames = By.xpath("//div[@class='col-lg-4 col-md-4 col-sm-4 center-block text-center ng-binding']");
//
//    By bookTypeRadioButtons = By.xpath("//input[@type='radio']");
//    By addBookButton = By.xpath("//button[@ng-click='newBook.create()']");

    //----------------------------------------------------------------------------------------------------------------//

    //Elements are ordered as they are displayed in application looking from top left page corner.
    //Most of elements have methods:
    // isDisplayed - Returns boolean if el is displayed on page
    // isPresent - If element is present in code
    // getElement - Return element itself

    //--------------------------------------------------PAGE TITLE----------------------------------------------------//

    public WebElement getTitle() { return pageTitle.get(0); }

    public boolean isDisplayedTitle() { return pageTitle.get(0).isDisplayed(); }

    public boolean isPresentTitle() { return pageTitle.size() == 1; }

    //------------------------------------CREATE PUBLICATION & ADD BOOK TABS------------------------------------------//

    public boolean isDisplayedCreatePublicationTab() { return createPublicationTab.get(0).isDisplayed(); }

    public boolean isDisplayedAddBookTab() { return addBookTab.get(0).isDisplayed(); }

    public void clickAddBookTab() { addBookTab.get(0).click(); }

    public WebElement getAddBookTabElement() { return addBookTab.get(0); }

    //-------------------------------------CREATE PUBLICATION TAB ELEMENTS--------------------------------------------//

    //------IS DISPLAYED METHODS------//

    public boolean isDisplayedCreatePublicationPanelTitle() { return createNewPublicationPanelTitle.get(0).isDisplayed(); }

    public boolean isDisplayedUploadFileButton() { return uploadBookCoverButton.get(0).isDisplayed(); }

    public boolean isDisplayedTitleInputField() { return titleInputField.get(0).isDisplayed(); }

    public boolean isDisplayedAuthorInputField() { return authorInputField.get(0).isDisplayed(); }

    public boolean isDisplayedISDNInputField() { return ISBNInputField.get(0).isDisplayed(); }

    public boolean isDisplayedDateInputField() { return publichDateInputField.get(0).isDisplayed();

    }

    public boolean isDisplayedCreatePublicationButton() { return createPublicationButton.get(0).isDisplayed(); }

    //-----INPUT TEXT INTO INPUT FIELDS-------//

    public void inputNewPublicationText(String title, String author, String ISBN, String publishDate) {
        titleInputField.get(0).sendKeys(title);
        authorInputField.get(0).sendKeys(author);
        ISBNInputField.get(0).sendKeys(ISBN);
        publichDateInputField.get(0).sendKeys(publishDate);
    }

    //-----IS PRESENT METHODS--------//

    public boolean isPresentCreatePublicationPanelTitle() { return createNewPublicationPanelTitle.get(0).isDisplayed(); }

    public boolean isPresentUploadFileButton() { return uploadBookCoverButton.size() > 0; }

    public boolean isPresentTitleInputField() { return titleInputField.size() > 0; }

    public boolean isPresentAuthorInputField() { return authorInputField.size() > 0; }

    public boolean isPresentISDNInputField() { return ISBNInputField.size() > 0; }

    public boolean isPresentDateInputField() { return publichDateInputField.size() > 0; }

    public boolean isPresentCreatePublicationButton() { return createPublicationButton.size() > 0; }

    //---------------------------------REMOVE BOOKS FROM LIBRARY SECTION (LIST OF BOOKS)------------------------------//

    public boolean isDisplayedRemoveBooksSubtitle() { return removeBooksSubtitle.get(0).isDisplayed(); }

    public boolean isDisplayedSearchfield() { return searchInputField.get(0).isDisplayed(); }

    public List<WebElement> getListOfBooks() { return bookTitleNames; }

    public By getBookDeleteButtonsLocator() { return deleteBookButtonsLocator; }

    public List<WebElement> getDeleteBookButtons() { return deleteBookButtons; }

    //----------------------------------------------ADD BOOK TAB------------------------------------------------------//

    public By getSelectedPublicationFieldsLocator() { return selectedPublicationFieldsLocator; }

    public List<WebElement> getSelectedPublicationFields() { return selectedPublicationFields; }

    public List<WebElement> getListOfBookTitles() { return bookTitleNames; }

    public List<WebElement> getSelectPublicationRadioButtons() { return bookTypeRadioButtons; }

    public void enterPublicationName(String name) { chooseTitleDropdown.get(0).sendKeys(name); }

    public WebElement getPublicationDropdownElement() { return chooseTitleDropdown.get(0); }

    public WebElement getOptionsDropdownElement() { return optionsDropdown.get(0); }

    public void clickTestTitlePublication() { testTitlePublication.get(0).click(); }

    public void clickAddBookButton() { addBookButton.get(0).click(); }




























}
