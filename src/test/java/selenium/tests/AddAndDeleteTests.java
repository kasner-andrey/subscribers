package selenium.tests;

import core.tests.BaseTest;
import mainClasses.Subscriber;
import mainClasses.SubscriberRandom;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SubscriberPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddAndDeleteTests extends BaseTest {
    private String baseUrl = "http://localhost:8081/";

    @Test(dataProvider = "subscribers")
    public void addSubscriber(Subscriber subscriber) {
        driver.get(baseUrl);
        List<Subscriber> before = new HomePage(driver, baseUrl)
                .subscriberLinkClick()
                .getAllSubscribers();

        assert subscriber.getGender().toValue() != null;
        SubscriberPage subscriberPage = new SubscriberPage(driver)
                .goToAdd()
                .firsNameSend(subscriber.getFirstName())
                .lastNameSend(subscriber.getLastName())
                .maleOrFemaleSend(subscriber.getGender().toValue())
                .ageSend(subscriber.getAge())
                .createClick();

        List<Subscriber> after = new SubscriberPage(driver)
                .getAllSubscribers();
        Assert.assertEquals(after.size(), before.size()+1);
        after.sort(Comparator.comparingInt(Subscriber::getId));
        before.add(after.get(after.size()-1));
        before.sort(Comparator.comparingInt(Subscriber::getId));
        Assert.assertEquals(after, before);
    }

//    @Test(dataProvider = "forDelete")
//    public void deleteSubscriber() {
//        driver.get(baseUrl);
//        List<Subscriber> before = new HomePage(driver, baseUrl)
//                .subscriberLinkClick()
//                .getAllSubscribers();
//
//        List
//    }

//    @DataProvider(name = "forDelete")
//    private Object[][] forDelete() {
//        driver.get(baseUrl);
//        List<Subscriber> before = new HomePage(driver, baseUrl)
//                .subscriberLinkClick()
//                .getAllSubscribers();
//        List<Integer> idForDelete = new ArrayList<>();
//        for (int i = 0; i < 1; i++) {
//            idForDelete.add();
//
//
//            }
//        }
//
//        return Object[][]{};
//    }



    @DataProvider(name = "subscribers")
    private Object[][] subscribers() {
        List<Subscriber> subscribers = new ArrayList<>();
        Subscriber subscriber;
        for (int i = 0; i < 1; i++) {
            subscriber = new SubscriberRandom().getSubscriber();
            subscribers.add(subscriber);
        }
        return new Object[][]{subscribers.toArray()};
    }


}
