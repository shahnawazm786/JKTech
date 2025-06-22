package healths;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.IConfig;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Listeners(listeners.ExtentReportListener.class)
public class HealthCheck {
    @BeforeMethod
    public void setup(){
        RestAssured.baseURI=IConfig.BASEURI;
    }
    @Test
    public void Test_Health_Check(){
                given()
                .header("Content-type", "application/json")
                .when().get(IConfig.health).
                then().statusCode(200).log().all()
                        .body("status",equalTo("up")).extract();
    }
}
