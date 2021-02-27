package mainClasses;

import java.util.ArrayList;
import java.util.List;

public class MainTestingAdd {
    public static void main(String[] args) {
        List<Subscriber> subscribers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            subscribers.add(new SubscriberRandom().getSubscriber());
        }
        System.out.println(subscribers);

    }
}
