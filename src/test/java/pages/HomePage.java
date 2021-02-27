package pages;

import core.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {
    private Logger LOG = LoggerFactory.getLogger(HomePage.class);
    private String baseUrl;
    @FindBy(xpath = "/div/a")
    private WebElement subscriberLink;

    public HomePage(WebDriver driver, String baseUrl) {
        super(driver);
        this.baseUrl = baseUrl;
    }

    public SubscriberPage subscriberLinkClick() {
        subscriberLink.click();
        return new SubscriberPage(driver);
    }
}
