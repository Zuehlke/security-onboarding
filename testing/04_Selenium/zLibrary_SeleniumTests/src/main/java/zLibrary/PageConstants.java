package zLibrary;

public interface PageConstants {

    String TESTING_URL = "http://zlibrary.eu-west-1.elasticbeanstalk.com";

    //Name and surname of user that access page
    String USER_NAME ="";
    String USER_SURNAME = "";

    //Browser
    String BROWSER = "chrome";

    //Driver path - These constants are obsolete due to usage of WebDriverManager library.
    //====================================================================================//
//    String DRIVER_PATH_CHROME = "drivers\\chromedriver.exe";
//    String DRIVER_PATH_FIREFOX_32 = "C:\\SeleniumWebdriver\\Selenium drivers\\geckodriver32.exe";
//    String DRIVER_PATH_FIREFOX_64 = "C:\\SeleniumWebdriver\\Selenium drivers\\geckodriver64.exe";
//    String DRIVER_PATH_IE = "C:\\SeleniumWebdriver\\Selenium drivers\\IEDriverServer.exe";
    //====================================================================================//
    //Temporary login solution, cookie strig
    String COOKIE_VALUE = "";

    // For testing search functionality on Library page
    String SEARCH_STRING_1 = "software ";
    String SEARCH_STRING_2 = "testing ";
    String SEARCH_STRING_3 = "advanced ";




}
