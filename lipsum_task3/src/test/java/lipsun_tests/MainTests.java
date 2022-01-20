package lipsun_tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class MainTests extends BaseTests {

    private final String SEARCH_KEYWORD = "рыба";
    private final String SEARCH_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
    private final String NOT_STARS_WITH = "Lorem ipsum";
    private final String SEARCH_KEYWORD_TWO = "lorem";
    private final String GENERATED_TEXT = "10";

    @Test
    public void checkThatCurrentWordAppearsInTheFirstParagraph() {
        getMainPage().chooseLanguage();
        getSearchResultsPage().getParagraphText();
        Assert.assertTrue(getSearchResultsPage().getParagraphText().getText().contains(SEARCH_KEYWORD));
    }

    @Test
    public void checkThatDefaultSettingResultAccordingToExpectations() {
        getMainPage().clickOnSubmitButton();
        getSearchResultsPage().getParagraphText();
        Assert.assertTrue(getSearchResultsPage().getParagraphText().getText().contains(SEARCH_TEXT));
    }

   @Test
    public void checkThatResultsInCheckboxNoStarsWithLoremIpsum() {
        getMainPage().clickOnCheckbox();
        getMainPage().clickOnCheckbox();
        getSearchResultsPage().getParagraphText();
      Assert.assertFalse(getSearchResultsPage().getParagraphText().getText().startsWith(NOT_STARS_WITH));
    }

    @Test
    public void checkGeneratedTextContainsCertainWordFortyPercent() {
        int count = 0;
        int targetCallsCount = 10;
        for (int i = 0; i < targetCallsCount; i++) {
            getMainPage().clickOnSubmitButton();
            List<WebElement> paragraphWebElements = getSearchResultsPage().getAllParagraphs();
            for (WebElement webElement : paragraphWebElements) {
                String text = webElement.getText();
                if (text.contains(SEARCH_KEYWORD_TWO)) {
                    count++;
                }
            }
            driver.get(LIPSUM_URL);
        }
        int expectedAverage = 2;
        double actualAverage = (double) count / targetCallsCount;
        Assert.assertTrue(actualAverage >= expectedAverage,
                String.format("Average number of paragraphs containing the word “%s” is less than %s. Actual average: %s",
                        SEARCH_KEYWORD_TWO, expectedAverage, actualAverage));
    }

    @Test
    public void checkGenerationWords(){
        getRadioButton().clickButton("bytes");
        getMainPage().inputText("10");
        getMainPage().clickOnSubmitButton();
        getSearchResultsPage().getGeneratedText().getText();
        Assert.assertTrue(getSearchResultsPage().getGeneratedText().getText().contains(GENERATED_TEXT));
    }

}




