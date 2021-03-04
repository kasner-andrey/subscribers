package pages;

import com.codeborne.selenide.SelenideElement;
import core.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.page;

public class AddPage extends BasePage {
    private final Logger LOG = LoggerFactory.getLogger(AddPage.class);

    @FindBy(xpath = "/html/body/div/div[2]/a")
    private WebElement homeLink;

    @FindBy(id = "fname")
    private WebElement firstNameField;

    @FindBy(id = "lname")
    private WebElement lastNameField;

    @FindBy(id = "FEMALE")
    private WebElement femaleRadioButton;

    @FindBy(id = "MALE")
    private WebElement maleRadioButton;

    @FindBy(id = "age")
    private WebElement ageField;

    @FindBy(xpath = "/html/body/div/form/button")
    private WebElement createButton;

    public AddPage firsNameSend(String firstName) {
        inputField(firstNameField, firstName);
        return this;
    }

    public AddPage(WebDriver driver) {
        super(driver);
    }

    public AddPage lastNameSend(String lastName) {
        inputField(lastNameField, lastName);
        return this;
    }

    public AddPage maleOrFemaleSend(String maleOrFemale) {
        if(maleOrFemale.equals("m")) maleRadioButton.click();
        else femaleRadioButton.click();
        return this;
    }

    public AddPage ageSend(int age){
        inputField(ageField, Integer.toString(age));
        return this;
    }

    public SubscriberPage createClick(){
        createButton.click();
        return new SubscriberPage(driver);
    }

    public HomePage goToHome() {
        homeLink.click();
        return new HomePage(driver, "");
    }




}
