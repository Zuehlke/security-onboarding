package zLibrary;

public interface PageConstants {

    String TESTING_URL = "http://zlibrary.eu-west-1.elasticbeanstalk.com";

    //Name and surname of user that access page
    String USER_NAME ="Marko";
    String USER_SURNAME = "Stojic";

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
    String COOKIE_VALUE = "%7B%22username%22:%22Stojic,%20Marko%22,%22authdata%22:%22eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IjdfWnVmMXR2a3dMeFlhSFMzcTZsVWpVWUlHdyIsImtpZCI6IjdfWnVmMXR2a3dMeFlhSFMzcTZsVWpVWUlHdyJ9.eyJhdWQiOiIwMWQ1ZjY4OC0yMWYzLTRmNjktOTQ1Zi0xNjZjNDlkMGQ4MWEiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC9jY2NlN2Y1ZS1hMzVmLTRiYzMtOGU2My1iMjIxNWU3ZDE0ZjkvIiwiaWF0IjoxNTM1NjEyMTI2LCJuYmYiOjE1MzU2MTIxMjYsImV4cCI6MTUzNTYxNjAyNiwiYWNyIjoiMSIsImFpbyI6IkFTUUEyLzhJQUFBQVZYTGEyT2JBL3BocmZnN1dhYWdtdS8zUEtmTzU5cWY5RU1VckpuVTFmZWs9IiwiYW1yIjpbInB3ZCJdLCJhcHBpZCI6IjAxZDVmNjg4LTIxZjMtNGY2OS05NDVmLTE2NmM0OWQwZDgxYSIsImFwcGlkYWNyIjoiMSIsImZhbWlseV9uYW1lIjoiU3RvamljIiwiZ2l2ZW5fbmFtZSI6Ik1hcmtvIiwiaXBhZGRyIjoiMTkzLjEzNS4yNTQuMTkiLCJuYW1lIjoiU3RvamljLCBNYXJrbyIsIm9pZCI6IjFiYzIzYTBiLWFkMGUtNGU2MC1iOTJjLWJiZmUxM2QwZDI1NSIsIm9ucHJlbV9zaWQiOiJTLTEtNS0yMS04NjE1Njc1MDEtMTUzMjI5ODk1NC0xODAxNjc0NTMxLTEwOTYwOSIsInNjcCI6IlVzZXIuUmVhZCIsInN1YiI6IkwyZFB0NHo4MUkycHhCRVYzR29oLS1xZmJ6WHdXbGxuanBFWHJNeUtoNkEiLCJ0aWQiOiJjY2NlN2Y1ZS1hMzVmLTRiYzMtOGU2My1iMjIxNWU3ZDE0ZjkiLCJ1bmlxdWVfbmFtZSI6Ik1hcmtvLlN0b2ppY0B6dWVobGtlLmNvbSIsInVwbiI6Ik1hcmtvLlN0b2ppY0B6dWVobGtlLmNvbSIsInV0aSI6InBKb3hOV240TkVPc1RteXhvNmg5QUEiLCJ2ZXIiOiIxLjAifQ.AJ6U2QZeJD6O5j_p6siWQEjHRK09u3xL2ZTxvVdvHyDrre_nSE7khUDUs8gfhrhGIaIWLF_oiYNmMipSdUiO3Oj4XLfRz9WvTCfcyTbKIIGOx6Zdyoi4Xx-2C1zy5OafJ0vQ4C-MxGAh2POjOfd32oOmWCEFDo0H8LXn_rnD3ey2dPNmvorW4PdPepaHRBkL0PdN1IZrXWurfvbspJMeeb2IuPqsKa1TMtvcmGpZZRhEoFyVQLels5lK7EJhHu657YX6FxZVlZGa04GXYZemXorenGg4UM7UT7V2u6YRyB1hoAZS-zuP-Y0BQNCZUPiKTGnXpXtUqXI3SGSlg4PgrA%22,%22role%22:%22ADMIN%22,%22id%22:%22117%22,%22refreshdata%22:%22AQABAAAAAADXzZ3ifr-GRbDT45zNSEFEhnzmOjcLkjryHw3KtMKVkCbzoB2nENhL4g398qvpGrWJwFpTX_-WaS7owkcalygUru0pIBNgWYwosf6T0W9u3Mwp9AO6nSybVIsIx4Bj8zLuMqNXDRkxpkk_PozfiI8bNgat7svh8iq2hIPHCLI-Pdxg3zmTaam3-B9T2scjY4sXizaxAaM-gyweVYb573jGJp0h5mxtaY663QBuJMaEmM6Wn8jDKTnOMS0UsQ33S9lEn8s5WT7KOwaL-k8Fj55ubz2hljIXPOWaT7krU4nqfqNO1LCkCRKCGc-_HqLP8PHReSu43RKKHt9VEgAPqpaOI_fKHkE3cjggoy8Oven3yP2WqSPbwT4xn3tvHsI4XKH78edXWbH99ysYWgvaar7K_F3kOzGHVSryo70pz6fT2Yl1b6eqC-gTGPbIexwN3QRSVlwF3mqwHcSNw2K0aE3ajU_VxLSmHFzF-ix0Vmc6pjvQLoodDq1VIHe7QI7jTAsl7eZ_8IF72-_mNfu6tue1InC85w7TzVfY9g7aNW36hQHpU2_whjcbjTyAB0-Uc_w2ctEzxyf1IoHHifeyCpME5_2w8bX4HUcxertdT6LUM6Z1nOrLiijragIgkTRjPzOYOwfITx6r1F0fSwHBOBR6Fiy-mDMznck17fhtNqJXkgUNSWB1eifJJok12I3TsRxFf5x0xynd2bYgsi6EPr3nJuLxWCQ8mLdCASNlEIFuhisBJUk_zspOCpZdgqZhRYDI_svzwt8Q9-FzmgTFyLkSazdp-Ci7YnPYtol5hiGSkCb3IYQc3YA-wogEQIbVao7GR3HxUZBwp7xWKquKDOYiarZ73Ju4l6IPtrKrSbq89yAA%22%7D";

    // For testing search functionality on Library page
    String SEARCH_STRING_1 = "software ";
    String SEARCH_STRING_2 = "testing ";
    String SEARCH_STRING_3 = "advanced ";




}
