package api.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import mainClasses.Gender;
import mainClasses.Subscriber;
import mainClasses.SubscriberRandom;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class ApiTest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost/rest/json";
        RestAssured.port = 8081;
    }

    @Test
    public void testGetOneSubscriber() {
        Response response = given()
                .get("/subscribers/{id}", 6686);

        String body = response.getBody().toString();
        int code = response.getStatusCode();
        int id = response.getBody().path("id" );
        String firstName = response.getBody().path("firstName");
        String lastName = response.getBody().path("lastName");

        System.out.println("Body: " + body);

        System.out.println(String.format("code: %s, id: %d, firstName: %s, lastName: %s",
                code, id, firstName, lastName));
    }

    @Test
    public void testGetSubscribers() {
        Response response = given()
                .log().all()
                .get("/subscribers");

        int code = response.getStatusCode();
        String body = response.getBody().toString();
        int size = response.getBody().path("size()");
        int id = response.getBody().path("[%s].id", "0");
        String fName = response.getBody().path("[%s].firstName", "0");
        String lastName = response.getBody().path("[%s].lastName", "0");

        System.out.println(code);
        System.out.println(body);
    }


    @Test(dataProvider = "subscriberProvider")
    public void testAddSubscriber(Subscriber subscriber) {
        //  1 шаг - получить тестового абонента (dataProvider)
        // if exists => delete
        if (getSubscriberById(subscriber.getId()) != null) {
            deleteSubscriber(subscriber.getId());
        }

        List<Subscriber> before = getAllSubscribers();

        System.out.println("*************************" + addSubscriber(subscriber));

        List<Subscriber> after = getAllSubscribers();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(subscriber);

        // отсортировать списки по id
//        after.sort((s1, s2)-> Integer.compare(s1.getId(), s2.getId()));
        before.sort(Comparator.comparingInt(Subscriber::getId));
        after.sort(Comparator.comparingInt(Subscriber::getId));
        Assert.assertEquals(after, before);
    }

    @Test
    public void testUpdateSubscriber() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id", 6681);
        jsonObj.put("firstName", "Ivan_upd"); // Cast
        jsonObj.put("lastName", "Ivanov_upd");
        jsonObj.put("age", 68);
        jsonObj.put("gender", "m");

        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(jsonObj.toJSONString())
                .put("/subscribers/6683")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test(dataProvider = "IdForDelete")
    public void testDeleteSubscriber(int id) {
        given()
                .log().all()
                .delete("/subscribers/" + id)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @DataProvider(name = "IdForDelete")
    private Object[][] IdForDelete() {



        return new Object[1][1];
    }

    private List<Subscriber> getAllSubscribers() {
        Response response = given().log().all()
                .get("/subscribers");
        if(response.getStatusCode() != 200){
            return null;
        }
        List<Subscriber> subscribers = new ArrayList<>();
        int size = response.getBody().path("size()");
        for (int i = 0; i < size; i++) {
            String index = String.valueOf(i);
            Subscriber subscriber = new Subscriber(
                    response.getBody().path("[%s].firstName", index)
                    , response.getBody().path("[%s].lastName", index)
                    , Gender.parse(response.getBody().path("[%s].gender", index))
                    , response.getBody().path("[%s].age", index));
            subscriber.setId(response.getBody().path("[%s].id", index));
            subscribers.add(subscriber);
        }
        return subscribers;
    }

    private Subscriber getSubscriberById(int id) {
        Response response = given().log().all()
                .get("/subscribers/" + id);
        if (response.getStatusCode() != 200) {
            return null;
        }
        Subscriber subscriber = new Subscriber(
                response.getBody().path("firstName")
                , response.getBody().path("lastName")
                , Gender.parse(response.getBody().path("gender"))
                , response.getBody().path("age")
        );
        subscriber.setId(response.getBody().path("id"));
        return subscriber;
    }

    private int deleteSubscriber(int id) {
        Response response = given().delete("/subscribers/{id}", id);
        return response.getStatusCode();
    }

    private int addSubscriber(Subscriber subscriber) {
        JSONObject newSubscriberJson = new JSONObject();
        newSubscriberJson.put("id", subscriber.getId());
        newSubscriberJson.put("firstName", subscriber.getFirstName());
        newSubscriberJson.put("lastName", subscriber.getLastName());
        newSubscriberJson.put("age", subscriber.getAge());
        newSubscriberJson.put("gender", subscriber.getGender().toValue());

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(newSubscriberJson.toJSONString())
                .post("/subscribers");

        return response.statusCode();
    }

    @DataProvider(name="subscriberProvider")
    public Object[][] subscriberProvider() {
        Object[][] subscribers = new Object[10][1];
        Subscriber subscriber;
        for (int i = 0; i < 10; i++) {
            subscriber = new SubscriberRandom().getSubscriber();
            subscriber.setId((int) (1 + Math.random() * 10000));
            subscribers[i][0] = subscriber;
        }
        return subscribers;
    }


}
