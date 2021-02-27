package pages;

import core.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdatePage extends BasePage {
    private Logger LOG = LoggerFactory.getLogger(UpdatePage.class);


    public UpdatePage(WebDriver driver) {
        super(driver);
    }
}
