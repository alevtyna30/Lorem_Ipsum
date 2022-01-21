package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.RadioButtonPage;
import pages.SearchResultPage;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DefinitionSteps {

    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    MainPage mainPage;
    RadioButtonPage radioButtonPage;
    SearchResultPage searchResultPage;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        mainPage = pageFactoryManager.getMainPage();
        mainPage.openMainPage(url);
    }

    @And("User choose language")
    public void UserChooseLanguage() {
        mainPage.chooseLanguage();
    }

    @When("User open generated page")
    public void UserOpenGeneratedPage() {
        searchResultPage = pageFactoryManager.getSearchResultPage();
        searchResultPage.getParagraphText();
    }

    @Then("User checks that current page contains {string}")
    public void UserChecksThatCurrentPageContainsSearchKeyword(final String searchKeyword) {
        assertTrue(searchResultPage.getParagraphText().getText().contains(searchKeyword));
    }

    @And("User clicks submit button")
    public void UserClicksSubmitButton() {
        mainPage.clickOnSubmitButton();
    }

    @Then("User checks that first paragraph starts with {string}")
    public void UserChecksThatFirstParagraphStartsWithSearchText(final String searchText) {
        assertTrue(searchResultPage.getParagraphText().getText().contains(searchText));
    }

    @And("User clicks checkbox button")
    public void UserClicksCheckboxButton() {
        mainPage.clickOnCheckbox();
    }

    @Then("User checks that text not starts with {string}")
    public void UserChecksThatTextNotStartsWithSearchWords(final String searchWords) {
        assertFalse(searchResultPage.getParagraphText().getText().startsWith(searchWords));
    }

    @And("User choose element {string} in the radio button group")
    public void UserChooseElementSearchKeywordInTheRadioButtonGroup(final String searchKeyword) {
        radioButtonPage = pageFactoryManager.getRadioButtonPage();
        radioButtonPage.clickButton(searchKeyword);
    }

    @And("User input {string} of chosen element")
    public void UserInputNumberOfChosenElement(final String inputNumber) {
        mainPage.inputText(inputNumber);
    }

    @When("User open generated link")
    public void UserOpenGeneratedLink() {
        searchResultPage = pageFactoryManager.getSearchResultPage();
        searchResultPage.getGeneratedText().getText();
    }

    @Then("User checks that generated text contains {string}")
    public void UserChecksThatGeneratedTextContainsNumber(final String inputNumber) {
        assertTrue(searchResultPage.getGeneratedText().getText().contains(inputNumber));
    }

    @After
    public void tearDown() {
        driver.close();
    }

}
