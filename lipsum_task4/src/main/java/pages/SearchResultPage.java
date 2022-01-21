package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(tagName = "p")
    private List<WebElement> allParagraphs;

    @FindBy(id = "generated")
    private WebElement generatedText;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getParagraphText() {
        return allParagraphs.get(0);
    }

    public List<WebElement> getAllParagraphs(){
        return allParagraphs;
    }

    public WebElement getGeneratedText(){
        return generatedText;
    }

}

