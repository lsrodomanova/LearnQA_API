import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserAgentTest {

    @ParameterizedTest
    @CsvSource({
            "Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML,Mobile,No,Android",
            "Mozilla/5.0 (iPad; CPU OS 13_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML,Mobile,Chrome,iOS",
            "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html),Googlebot,Unknown,Unknown",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML,Web,Chrome,No",
            "Mozilla/5.0 (iPad; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML,Mobile,No,iPhone"
    })

    public void checkUserAgent(String agent, String platform, String browser, String device) {

            Map<String, String> data = new HashMap<>();
            data.put("User-Agent", agent);

            JsonPath response = RestAssured
                    .given()
                    .header("User-Agent", agent)
                    .get("https://playground.learnqa.ru/ajax/api/user_agent_check")
                    .jsonPath();
            String userAgent = response.get("user_agent");
            String checkPlatform = response.get("platform");
            String checkBrowser = response.get("browser");
            String checkDevice = response.get("device");
            assertEquals(agent, userAgent,"Agent failed");
            assertEquals(platform, checkPlatform,userAgent);
            assertEquals(browser, checkBrowser,userAgent);
            assertEquals(device, checkDevice,userAgent);

    }

}