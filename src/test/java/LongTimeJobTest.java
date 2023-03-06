import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;


public class LongTimeJobTest {
    String token;
    String url="https://playground.learnqa.ru/ajax/api/longtime_job";
    @Test
    public void getTask() throws InterruptedException {
        JsonPath response = RestAssured
                .get(url)
                .jsonPath();
        int time = response.get("seconds");
        int millis = time * 1000;
        token = response.get("token");
        System.out.println(time);

        JsonPath response2 = RestAssured
                .given()
                .queryParam("token", token)
                .get(url)
                .jsonPath();
        String status = response2.get("status");
        System.out.println(status);
        Thread.sleep(millis);

            JsonPath response3 = RestAssured
                    .given()
                    .queryParam("token", token)
                    .get(url)
                    .jsonPath();
            String status2 = response3.get("status");
            String result = response.get("result");
            System.out.println(status2);
        if (result == null) {
            System.out.println("Result is null");
        }
        else {
            System.out.println(result);
        }
    }
}

