import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewHeadersTest {

    @Test
    public void getCookie() {

            Response response = RestAssured
                    .get("https://playground.learnqa.ru/api/homework_header")
                    .andReturn();
            Headers answer = response.getHeaders();
            System.out.println(answer);
            String secretHeader= response.getHeader("x-secret-homework-header");
            String server = response.getHeader("Server");
            String contentType = response.getHeader("Content-Type");
            String contentLength = response.getHeader("Content-Length");
            assertEquals("Some secret value",secretHeader, "Test failed" );
            assertEquals("Apache",server, "Test failed" );
            assertEquals("application/json",contentType, "Test failed" );
            assertEquals("15",contentLength, "Test failed" );
        }
    }
