package zLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Navigation {

    WebDriver driver;

    @FindBy(how = How.XPATH, using = "//a[text()='Library Editor ']")
    @CacheLookup
    List<WebElement> navLibraryEditor;

    @FindBy(how = How.XPATH, using = "//a[text()='Rented overview ']")
    @CacheLookup
    List<WebElement> navRentedOverview;

    @FindBy(how = How.XPATH, using = "//a[text()='User Manager ']")
    @CacheLookup
    List<WebElement> navUserManager;

    @FindBy(how = How.XPATH, using = "//a[text()='Library ']")
    @CacheLookup
    List<WebElement> navLibrary;

    @FindBy(how = How.XPATH, using = "//a[text()='My Rented Items ']")
    @CacheLookup
    List<WebElement> navMyRentedItems;

    @FindBy(how = How.XPATH, using = "//a[text()='Order a Book ']")
    @CacheLookup
    List<WebElement> navOrderBook;

    @FindBy(how = How.XPATH, using = "//a[text()='About ']")
    @CacheLookup
    List<WebElement> navAbout;

    @FindBy(how = How.XPATH, using = "//span[text()='Logout ']")
    @CacheLookup
    List<WebElement> navLogout;

    @FindBy(how = How.XPATH, using = "//div[text()='zLibrary']")
    @CacheLookup
    List<WebElement> libraryLogo;

    @FindBy(how = How.XPATH, using = "//a[text()='Stojic, Marko ']")
    @CacheLookup
    List<WebElement> usernameInfo;

    public Navigation(WebDriver driver) {
        this.driver = driver;
    }

    // Links are written by their order in page header. Every have three methods:
    // Click - Clicks on link
    // IsDisplayed - Checks if link is displayed (Precondition is that it is present in code)
    // IsPresent - Checks if link is present in code
    // getElement - Return element itself

    //----------------------------LIBRARY LOGO---------------------------------//

    public boolean isPresentLibLogo() { return libraryLogo.size() == 1; }

    public boolean isDisplayedLibLogo() { return libraryLogo.get(0).isDisplayed(); }

    public WebElement getLibLogo() { return libraryLogo.get(0); }

    //----------------------------LIBRARY LINK---------------------------------//

    public void clickLibrary() { navLibrary.get(0).click(); }

    public boolean isDisplayedLibrary() { return navLibrary.get(0).isDisplayed(); }

    public boolean isPresentLibrary() { return navLibrary.size() == 1; }

    public WebElement getLibraryLinkElement() { return navLibrary.get(0); }

    //----------------------------MY RENTED ITEMS LINK----------------------------//

    public void clickMyRentedItems() { navMyRentedItems.get(0).click(); }

    public boolean isDisplayedMyRentedItems() { return navMyRentedItems.get(0).isDisplayed(); }

    public boolean isPresentMyRentedItems() { return navMyRentedItems.size() == 1; }

    public WebElement getMyRentedItemsElement() { return navMyRentedItems.get(0); }

    //--------------------------ORDER BOOK LINK-----------------------------------//

    public void clickOrderBook() { navOrderBook.get(0).click(); }

    public boolean isPresentOrderBook() { return navOrderBook.size() == 1; }

    public boolean isDisplayedOrderBook() { return navOrderBook.get(0).isDisplayed(); }

    public WebElement getOrderBookLinkElement() { return navOrderBook.get(0); }

    //-----------------------ABOUT LINK-------------------------------------------//

    public void clickAbout() { navAbout.get(0).click(); }

    public boolean isPresentAbout() { return navAbout.size() == 1; }

    public boolean isDisplayedAbout() { return navAbout.get(0).isDisplayed(); }

    public WebElement getAboutElement() { return navAbout.get(0); }

    //----------------------------LIBRARY EDITOR LINK--------------------------------//

    public void clickLibraryEditor() { navLibraryEditor.get(0).click(); }

    public boolean isPresentLibraryEditor() { return navLibraryEditor.size() == 1; }

    public boolean isDisplayedLibraryEditor() { return navLibraryEditor.get(0).isDisplayed(); }

    public WebElement getLibraryEditorElement() { return navLibraryEditor.get(0); }

    //--------------------------RENTED OVERVIEW----------------------------//

    public void clickRentedOverview() { navRentedOverview.get(0).click(); }

    public boolean isPresentRentedOverview() { return navRentedOverview.size() == 1; }

    public boolean isDisplayedRentedOverview() { return navRentedOverview.get(0).isDisplayed(); }

    public WebElement getRentedOverviewLinkElement() { return navRentedOverview.get(0); }

    //---------------------------USER MANAGER------------------------------------//

    public void clickUserManager() { navUserManager.get(0).click(); }

    public boolean isPresentUserManager() { return navUserManager.size() == 1; }

    public boolean isDisplayedUserManager() { return navUserManager.get(0).isDisplayed(); }

    public WebElement getUserManagerElement() { return navUserManager.get(0); }

    //-------------------------USERNAME DISPLAY------------------------------------------//

    // Returns new String that has first character uppercase, rest are lowercase.
    private String capitalize(String str) {
        char first = str.charAt(0);
        first = Character.toUpperCase(first);
        String rest = str.substring(1);
        rest = rest.toLowerCase();
        return first + rest;
    }

    private String usernameInfoXpath(String name, String surname) {
        String nameFinal = capitalize(name);
        String surnameFinal = capitalize(surname);
        return "//a[text()='" + surnameFinal + ", " + nameFinal + " ']";
    }

    public boolean isPresentUsername(String name, String surname) {
        String generatedXpath = usernameInfoXpath(name, surname);
        return driver.findElements(By.xpath(generatedXpath)).size() == 1;
    }

    public boolean isDisplayedUsername(String name, String surname) {
        String generatedXpath = usernameInfoXpath(name, surname);
        return driver.findElement(By.xpath(generatedXpath)).isDisplayed();
    }

    //------------------------LOGOUT LINK-----------------------------------//

    public boolean isPresentLogout() { return navLogout.size() == 1; }

    public void clickLogout() { navLogout.get(0).click(); }

    public boolean isDisplayedLogout() { return navLogout.get(0).isDisplayed(); }



}