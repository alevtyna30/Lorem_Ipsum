import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

public class LipsumTests {

    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lipsum.com/");
    }

    @Test
    public void checkThatCurrentWordAppearsInTheFirstParagraph(){
        driver.findElement(By.xpath("//a[@href='http://ru.lipsum.com/']")).click();
        String elementText = driver.findElement(By.tagName("p")).getText();
        Assert.assertTrue(elementText.contains("рыба"));
    }

    @Test
    public void checkThatDefaultSettingResultAccordingToExpectations(){
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String elementText = driver.findElement(By.tagName("p")).getText();
        Assert.assertTrue(elementText.contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit"));
    }

    @Test
    public void checkGenerationWordsWithTenWords(){
        driver.findElement(By.xpath("//label[@for='words']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("10");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String elementText = driver.findElement(By.id("generated")).getText();
        Assert.assertTrue(elementText.contains("10 words"));
    }

    @Test
    public void checkGenerationWordsWithMinusOneWord(){
        driver.findElement(By.xpath("//label[@for='words']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("-1");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String elementText = driver.findElement(By.id("generated")).getText();
        Assert.assertTrue(elementText.contains("5 words"));
    }

    @Test
    public void checkGenerationWordsWithZeroWord(){
        driver.findElement(By.xpath("//label[@for='words']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("0");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String elementText = driver.findElement(By.id("generated")).getText();
        Assert.assertTrue(elementText.contains("5 words"));
    }

    @Test
    public void checkGenerationWordsWithFiveWord(){
        driver.findElement(By.xpath("//label[@for='words']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("5");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String elementText = driver.findElement(By.id("generated")).getText();
        Assert.assertTrue(elementText.contains("5 words"));
    }

    @Test
    public void checkGenerationWordsWithTwentyWord(){
        driver.findElement(By.xpath("//label[@for='words']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("20");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String elementText = driver.findElement(By.id("generated")).getText();
        Assert.assertTrue(elementText.contains("20 words"));
    }

    @Test
    public void checkGenerationWordsForCharactersWithTwentyWord(){
        driver.findElement(By.xpath("//label[@for='bytes']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("20");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String elementText = driver.findElement(By.id("generated")).getText();
        Assert.assertTrue(elementText.contains("20 bytes"));
    }

    @Test
    public void checkGenerationWordsForCharactersWithTenWord(){
        driver.findElement(By.xpath("//label[@for='bytes']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("10");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String elementText = driver.findElement(By.id("generated")).getText();
        Assert.assertTrue(elementText.contains("10 bytes"));
    }

    @Test
    public void checkGenerationWordsForCharactersWithMinusOneWord(){
        driver.findElement(By.xpath("//label[@for='bytes']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("-1");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String elementText = driver.findElement(By.id("generated")).getText();
        Assert.assertTrue(elementText.contains("5 bytes"));
    }

    @Test
    public void checkThatResultsInCheckboxNoStarsWithLoremIpsum(){
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String elementText = driver.findElement(By.tagName("p")).getText();
        Assert.assertFalse(elementText.startsWith("Lorem ipsum"));
    }

    @Test
    public void checkGeneratedTextContainsCertainWordFortyPercent() {
        String searchWord = "lorem";

        int count = 0;
        int targetCallsCount = 10;
        for (int i = 0; i < targetCallsCount; i++) {
            driver.findElement(By.xpath("//input[@type='submit']")).click();
            List<WebElement> paragraphWebElements = driver.findElements(By.tagName("p"));

            for (WebElement webElement : paragraphWebElements) {
                String text = webElement.getText();
                if (text.contains(searchWord)) {
                    count++;
                }
            }

            driver.get("https://www.lipsum.com/");
        }

        int expectedAverage = 2;
        double actualAverage = (double) count / targetCallsCount;
        Assert.assertTrue(actualAverage >= expectedAverage,
                String.format("Average number of paragraphs containing the word “%s” is less than %s. Actual average: %s",
                        searchWord, expectedAverage, actualAverage));
    }

    @AfterMethod
    public void tearDown () {
        driver.quit();
    }
}

