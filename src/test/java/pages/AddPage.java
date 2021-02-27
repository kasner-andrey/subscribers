package pages;

import core.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddPage extends BasePage {
    private Logger LOG = LoggerFactory.getLogger(AddPage.class);
    public AddPage(WebDriver driver) {
        super(driver);
    }
}
