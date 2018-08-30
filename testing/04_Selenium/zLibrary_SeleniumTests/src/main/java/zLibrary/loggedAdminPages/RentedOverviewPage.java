package zLibrary.loggedAdminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class RentedOverviewPage {


    @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'List of items rented from the library')]")
    List<WebElement> pageTitle;

    @FindBy(how = How.CSS, using = "h2[class='panel-title']")
    List<WebElement> panelTitle;

    @FindBy(how = How.CSS, using = "input[placeholder='Search']")
    List<WebElement> searchInputField;

    @FindBy(how = How.XPATH, using = "//div[@class='row ng-scope']/div/strong")
    List<WebElement> listedBooksTitle;

    @FindBy(how = How.XPATH, using = "//div[@class='row ng-scope']/div[2]/i")
    List<WebElement> listedBooksAuthor;

    @FindBy(how = How.XPATH, using = "//div[@class='row ng-scope']/div[3]")
    List<WebElement> listedBooksRentee;

    @FindBy(how = How.XPATH, using = "//div[@class='row ng-scope']/div[5]/button")
    List<WebElement> listedBooksReturnButton;

    By listedBooksReturnButtonLocator = By.xpath("//div[@class='row ng-scope']/div[5]/button");

//    By pageTitle = By.xpath("//h2[contains(text(), 'List of items rented from the library')]");
//    By panelTitle = By.xpath("//h2[@class='panel-title']");
//    By searchInputField = By.xpath("//input[@placeholder='D_Search']");
//    By listedBooksTitle = By.xpath("//div[@class='row ng-scope']/div/strong");
//    By listedBooksAuthor = By.xpath("//div[@class='row ng-scope']/div[2]/i");
//    By listedBooksRentee = By.xpath("//div[@class='row ng-scope']/div[3]");
//    By listedBooksReturnButton = By.xpath("//div[@class='row ng-scope']/div[5]/button");
// By listedBooksTitileBootomUp = By.xpath("//button[@class='btn btn-custom']/parent::div/preceding-sibling::div[5]"); // Example of bottom up approach using XPath

    //Elements are ordered as they are displayed in application looking from top left page corner.
    //Most of elements have methods:
    // isDisplayed - Returns boolean if el is displayed on page
    // isPresent - If element is present in code
    // getElement - Return element itself

    //--------------------------------------------------PAGE TITLE----------------------------------------------------//

    public boolean isPresentPageTitle() { return pageTitle.size() > 0; }

    public boolean isDisplayedTitle() { return pageTitle.get(0).isDisplayed(); }

    public WebElement getTitle() { return pageTitle.get(0); }

    //-----------------------------------------------PANEL TITLE------------------------------------------------------//

    public boolean isDisplayedPanelTitle() { return panelTitle.get(0).isDisplayed(); }

    //---------------------------------------------SEARCH FIELD-------------------------------------------------------//

    public void inputSearchString(String text) { searchInputField.get(0).sendKeys(text); }

    public boolean isPresentSearchField() { return searchInputField.size() > 0; }

    public boolean isDisplayedSearchField() { return searchInputField.get(0).isDisplayed(); }

    //---------------------------------------------BOOKS LIST---------------------------------------------------------//

    public List<WebElement> getBooksTitles() { return listedBooksTitle; }

    public List<WebElement> getBooksAuthors() { return listedBooksAuthor; }

    public List<WebElement> getBooksRentees() { return listedBooksRentee; }

    public List<WebElement> getBooksReturnButtons() { return listedBooksReturnButton; }

    public By getBookReturnButtonsLocator() { return listedBooksReturnButtonLocator; }

    public boolean isTitlePresent(String titleName) {
        List<WebElement> books = listedBooksTitle;
        for(int i = 0; i < books.size(); i++) {
            String bookTitle = books.get(i).getText();
            if(bookTitle.contains(titleName) || titleName.contains(bookTitle)) return true;
        }
        return false;
    }

    // Input unique titleName so this expression will work!
    public boolean isTitleFoundInSearch(String titleName) {
        List<WebElement> books = listedBooksTitle;
        if(books.size() == 1 && books.get(0).getText().contains(titleName)) return true;
        return false;
    }

    // IMPORTANT: Call searchTitle first, verify that it is found with isTitleFoundInSearch and then call this method!!!
    public void returnSearchedTitle(String titleName) {
        List<WebElement> books = listedBooksReturnButton;
        books.get(0).click();
    }

}
