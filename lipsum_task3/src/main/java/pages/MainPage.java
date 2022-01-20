package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{

    @FindBy(xpath = "//a[@href='http://ru.lipsum.com/']")
    private WebElement language;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkbox;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement inputField;




    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void chooseLanguage(){
        language.click();
    }

    public void clickOnSubmitButton(){
        submitButton.click();
    }

    public void clickOnCheckbox(){
        checkbox.click();
    }

    public void inputText(String key){
        inputField.clear();
        inputField.sendKeys(key);
    }

}
