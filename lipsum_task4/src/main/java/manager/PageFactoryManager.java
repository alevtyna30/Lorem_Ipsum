package manager;

import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.RadioButtonPage;
import pages.SearchResultPage;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage getMainPage(){
        return new MainPage(driver);
    }

    public RadioButtonPage getRadioButtonPage(){
        return new RadioButtonPage(driver);
    }

    public SearchResultPage getSearchResultPage(){
        return new SearchResultPage(driver);
    }

}
