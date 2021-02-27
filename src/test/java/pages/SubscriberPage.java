package pages;

import core.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberPage extends BasePage {
    private Logger LOG = LoggerFactory.getLogger(SubscriberPage.class);

    public SubscriberPage(WebDriver driver) {
        super(driver);
    }
}
