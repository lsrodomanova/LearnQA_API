import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NewCookieTest {

    @Test
    public void getCookie() {

            Response response = RestAssured
                    .get("https://playground.learnqa.ru/api/homework_cookie")
                    .andReturn();
            Map answer = response.getCookies();
            System.out.println(answer);
            String cookie = response.getCookie("HomeWork");
            assertEquals("hw_value",cookie, "Test failed" );
        }
    }
