package pages;



import com.codeborne.selenide.SelenideElement;
import core.page.BasePage;
import mainClasses.Subscriber;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utill.PropertyProvider;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.page;


public class SubscriberPage extends BasePage {
    private final Logger LOG = LoggerFactory.getLogger(SubscriberPage.class);

    @FindBy(css = "a[name='contact-edit-id']")
    private List<WebElement> idS;

    @FindBy(css = "tr > td:nth-child(3)")
    private List<WebElement> fNames;

    @FindBy(xpath = "//*[@id=\"add\"]")
    private WebElement addButton;

    @FindBy(id = "del")
    private WebElement delButton;

    @FindBy(xpath = "/html/body/div/div[2]/a")
    private WebElement homeLink;


    public SubscriberPage(WebDriver driver) {
        super(driver);
    }

    public List<Subscriber> getAllSubscribers() {
        List<Subscriber> subscribers = new ArrayList<>();
        for (int i = 0; i < idS.size(); i++) {
            Subscriber subscriber = new Subscriber();
            subscriber.setId(Integer.parseInt(idS.get(i).getText()));
            subscriber.setFirstName(fNames.get(i).getText());
            subscribers.add(subscriber);
        }
        return subscribers;
    }

    public Subscriber getSubscriberById(int id) {
        String fName = driver.findElement(By.xpath(String.format(PropertyProvider.get("fNameByIdXPathTempl"), id))).getText();
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName(fName);
        return subscriber;
    }

    public HomePage goToHome() {
        homeLink.click();
        return new HomePage(driver, "");
    }

    public AddPage goToAdd(){
        addButton.click();
        return new AddPage(driver);
    }

    public SubscriberPage goToDel(){
        delButton.click();
        return this;
    }



}
