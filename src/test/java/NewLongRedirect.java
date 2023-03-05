import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class NewLongRedirect {
    @Test
    public void testLongRedirect() {
        int i=0;
        int statusCode;
            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get("https://playground.learnqa.ru/api/long_redirect")
                    .andReturn();
            String location = response.getHeader("Location");
            statusCode = response.getStatusCode();
            i++;

        while (statusCode != 200) {
            Response response2 = RestAssured
                    .given()
                    .redirects()
                    .follow(true)
                    .when()
                    .get(location)
                    .andReturn();
            statusCode = response2.getStatusCode();
            i++;
            System.out.println(i);
        }
    }
}

