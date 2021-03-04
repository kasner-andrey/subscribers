package mainClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTestingAdd {
    public static void main(String[] args) {
//        List<Subscriber> subscribers = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            subscribers.add(new SubscriberRandom().getSubscriber());
//        }
//        System.out.println(subscribers);





        Object[][] subscribers1 = new Object[10][1];
        Subscriber subscriber;
        for (int i = 0; i < 10; i++) {
            subscriber = new SubscriberRandom().getSubscriber();
            subscriber.setId((int) (1 + Math.random() * 10000));
            subscribers1[i][0] = subscriber;
        }
        System.out.println(Arrays.deepToString(subscribers1));
    }
}
