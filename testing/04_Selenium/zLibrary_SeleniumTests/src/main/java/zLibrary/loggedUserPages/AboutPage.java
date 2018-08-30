package zLibrary.loggedUserPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class AboutPage {

    @FindBy(how =  How.XPATH, using = "//h2[contains(text(), 'About zLibrary')]")
    List<WebElement> pageTitle;

    @FindBy(how =  How.XPATH, using = "//h4[contains(text(), 'Ordering new books')]")
    List<WebElement> pageSubtitleNewBooks;

    @FindBy(how =  How.XPATH, using = "//h4[contains(text(), 'Contact developers team')]")
    List<WebElement> pageSubtitleContactDev;

//    By pageTitle = By.xpath("//h2[contains(text(), 'About zLibrary')]");
//    By pageSubtitleNewBooks = By.xpath("//h4[contains(text(), 'Ordering new books')]");
//    By pageSubtitleContactDev = By.xpath("//h4[contains(text(), 'Contact developers team')]");

    //Elements are ordered as they are displayed in application looking from top left page corner.
    //Most of elements have methods:
    // isDisplayed - Returns boolean if el is displayed on page
    // isPresent - If element is present in code
    // getElement - Return element itself

    //-------------------------------------------------PAGE TITLE-----------------------------------------------------//

    public boolean isPresentTitle() { return pageTitle.size() > 1; }

    public boolean isDisplayedPageTitle() { return pageTitle.get(0).isDisplayed(); }

    public WebElement getTitle() { return pageTitle.get(0); }

    //-----------------------------------------------NEW BOOKS SECTION------------------------------------------------//

    public boolean isPresentNewBooks() { return pageSubtitleNewBooks.size() > 0; }

    public boolean isDisplayedNewBooks() { return pageSubtitleNewBooks.get(0).isDisplayed(); }

    //----------------------------------------------DEV SECTION-------------------------------------------------------//

    public boolean isPresentDev() { return pageSubtitleContactDev.size() > 0; }

    public boolean isDisplayedDev() { return pageSubtitleContactDev.get(0).isDisplayed(); }














}
