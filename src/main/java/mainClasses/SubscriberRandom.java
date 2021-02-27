package mainClasses;

import utill.CSVProvider;
import utill.PropertyProvider;

import java.util.Random;

public class SubscriberRandom {
    private final Random random = new Random();
    private Subscriber subscriber;

    public Subscriber getSubscriberGender(Gender gender) {
        return subscriberRandom(gender);
    }

    public Subscriber getSubscriber() {
        int maleOrFemale = random.nextInt(101); //от 0 до 100 рандомный выбор мужчина - женщина (на этом основывается дальнейшее разделения выбора списков файлов)
        Gender gender = Gender.FEMALE; // по умолчанию - женщина
        if (maleOrFemale > 50) gender = Gender.MALE; // если рандом болеее 50 то пол будет мужчина
        return subscriberRandom(gender);
    }

    private Subscriber subscriberRandom(Gender gender){
        subscriber = new Subscriber();
        switch (gender){
            case FEMALE: subscriber.setFirstName(CSVProvider.get(random.nextInt(331), "female.firstName")[0]);
                         subscriber.setLastName(CSVProvider.get(random.nextInt(104), "female.lastName")[0]);
                         subscriber.setGender(gender);
                         break;
            case MALE:   subscriber.setFirstName(CSVProvider.get(random.nextInt(327), "male.firstName")[0]);
                         subscriber.setLastName(CSVProvider.get(random.nextInt(249), "male.lastName")[0]);
                         subscriber.setGender(gender);
                         break;
        }
        subscriber.setAge(subscriberAge());
        return subscriber;
    }

    private int subscriberAge() {
        Double ageTo = Double.parseDouble(PropertyProvider.get("age.to"));
        Double ageFrom = Double.parseDouble(PropertyProvider.get("age.from"));
        int gaussRand = (int) ((ageTo - ageFrom) / 2 // формула для данных от 5 до 90 лет = ((90-5)/2) + (random.nextGaussian() * (((90-5)/2)/3))
                + (random.nextGaussian() * (ageTo - ageFrom) / 6));
        if(gaussRand < ageFrom || gaussRand > ageTo) gaussRand = subscriberAge();
        return gaussRand; // если параметры рандома выходят за рамки, возвращаем к функции для повторной генерации рандома

    }
}
