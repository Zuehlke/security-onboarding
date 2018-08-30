package zLibrary.loggedUserPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class OrderBookPage {

    @FindBy(how = How.XPATH, using = "//h2[text()='Order books']")
    List<WebElement> pageTitle;

    @FindBy(how = How.CSS, using = "input[id='bookTitleInput']")
    List<WebElement> titleInputField;

    @FindBy(how = How.CSS, using = "input[id='bookAuthorInput']")
    List<WebElement> authorInputField;

    @FindBy(how = How.CSS, using = "input[id='bookAmazonUrlInput']")
    List<WebElement> URLInputField;

    @FindBy(how = How.CSS, using = "input[type='radio']")
    List<WebElement> radioButtons;

    By radioButtonsLocator = By.cssSelector("input[type='radio']");

    @FindBy(how = How.CSS, using = "h3[class='panel-bookTitle']")
    List<WebElement> orderBookPanelTitle;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Reset Fields')]")
    List<WebElement> resetFieldsButton;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Order')]")
    List<WebElement> orderButton;

//    By pageTitle = By.xpath("//h2[text()='Order books']");
//    By titleInputField = By.xpath("//input[@id='bookTitleInput']");
//    By authorInputField = By.xpath("//input[@id='bookAuthorInput']");
//    By URLInputField = By.xpath("//input[@id='bookAmazonUrlInput']");
//    By radioButtons = By.xpath("//input[@type='radio']");
//    By orderBookPanelTitle = By.xpath("//h3[@class='panel-bookTitle']");
//    By resetFieldsButton = By.xpath("//button[contains(text(), 'Reset Fields')]");
//    By orderButton = By.xpath("//button[contains(text(), 'Order')]");

    //Elements are ordered as they are displayed in application looking from top left page corner.
    //Most of elements have methods:
    // isDisplayed - Returns boolean if el is displayed on page
    // isPresent - If element is present in code
    // getElement - Return element itself

    //-------------------------------------------------PAGE TITLE-----------------------------------------------------//

    public boolean isPresentTitle() { return pageTitle.size() > 0; }

    public boolean isDisplayedTitle() { return pageTitle.get(0).isDisplayed(); }

    public WebElement getTitle() { return pageTitle.get(0); }

    //-------------------------------------------------PANEL TITLE-----------------------------------------------------//

    public boolean isDisplayedPanelTitle() { return orderBookPanelTitle.get(0).isDisplayed(); }

    public boolean isPresentPanelTitle() { return orderBookPanelTitle.size() > 0; }

    //---------------------------------------------ORDER BOOK FORM FIELDS---------------------------------------------//

    public void fillOrderBookform(String title, String author, String amazonURL) {
        inputTitle(title);
        inputAuthor(author);
        inputURL(amazonURL);
    }

    public void inputTitle(String text) { titleInputField.get(0).sendKeys(text); }

    public boolean isDisplayedInputTitle() { return titleInputField.get(0).isDisplayed(); }

    public void inputAuthor(String text) { authorInputField.get(0).sendKeys(text); }

    public boolean isDisplayedInputAuthor() { return authorInputField.get(0).isDisplayed(); }

    public void inputURL(String text) { URLInputField.get(0).sendKeys(text); }

    public boolean isDisplayedInputURL() { return URLInputField.get(0).isDisplayed(); }

    //----------SECTION RADIO BUTTONS------------//

    // Clicks on "index" radio button in row from left to right, if index is > 3 || < 0 default value is 1st.
    public void clickRadioButton(int index) {
        List<WebElement> buttons = radioButtons;
        switch (index) {
            case 0: buttons.get(0).click(); break;
            case 1: buttons.get(1).click(); break;
            case 2: buttons.get(2).click(); break;
            default: buttons.get(0).click();
        }
    }

    public boolean areDisplayedRadioButtons() {
        List<WebElement> buttons = radioButtons;
        return buttons.size() == 3;
    }

    public By getRadioButtonsLocator() { return radioButtonsLocator; }

    //----------SECTION BUTTONS----------------//


    public void clickResetFieldsButton() { resetFieldsButton.get(0).click(); }

    public boolean isDisplayedResetFieldsButton() { return resetFieldsButton.get(0).isDisplayed(); }

    public void clickOrderButton() { orderButton.get(0).click(); }

    public boolean isDisplayedOrderButton() { return orderButton.get(0).isDisplayed(); }

    public boolean isEnabledOrderButton() { return orderButton.get(0).isEnabled(); }

}
