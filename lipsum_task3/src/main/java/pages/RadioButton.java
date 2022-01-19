package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RadioButton extends BasePage {
    private WebDriver driver;

    @FindBy(xpath = "//td[@rowspan='2']//tbody")
    private WebElement radioButtonGroup;

    public RadioButton(WebDriver driver) {
        super(driver);
    }

    public void clickButton(String buttonName) {
        radioButtonGroup.findElement(By.xpath(String.format("//input[@id='%s']", buttonName))).click();

    }

}

