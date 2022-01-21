package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RadioButtonPage extends BasePage {
    @FindBy(xpath = "//td[@rowspan='2']//tbody")
    private WebElement radioButtonGroup;

    public RadioButtonPage(WebDriver driver) {
        super(driver);
    }

    public void clickButton(String buttonName) {
        radioButtonGroup.findElement(By.xpath(String.format("//input[@id='%s']", buttonName))).click();
    }

}
