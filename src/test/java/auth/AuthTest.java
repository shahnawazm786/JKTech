package auth;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.IConfig;
import utils.Payload;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
@Listeners(listeners.ExtentReportListener.class)
public class AuthTest {
    @BeforeMethod
    public void setup(){
        RestAssured.baseURI=IConfig.BASEURI;
    }
    @Test
    public void Test_Signup(){

        Response resp = given()
                .header("Content-type", "application/json")
               // .body(new File(IConfig.SignupFile))
                .body(Payload.SignupPayload())
                .log().all()
                .post(IConfig.Signup).then().statusCode(200).log().all().extract().response();
        //System.out.println(resp.asPrettyString());
        JsonPath path=resp.jsonPath();
        String msg=path.getString("message");
        assertEquals(msg,"User created successfully");
     }
     @Test
    public void Test_Signup_Negative(){
         Response resp = given()
                 .header("Content-type", "application/json")
                 .body(new File(IConfig.SignupFile))
                 .log().all()
                 .post(IConfig.Signup).then().statusCode(400).log().all().extract().response();
         JsonPath path=resp.jsonPath();
         String msg=path.getString("detail");
         assertEquals(msg,"Email already registered");
     }
    @Test
    public void Test_Login(){
        Response resp = given()
                .header("Content-type", "application/json")
                .body(new File(IConfig.LoginFile))
                .log().all()
                .post(IConfig.login).then().statusCode(200).log().all().extract().response();
        JsonPath path=resp.jsonPath();
        assertEquals(path.getString("token_type"),"bearer");
    }
    @Test
    public void Test_Login_Negative(){
        Response resp = given()
                .header("Content-type", "application/json")
                .body(Payload.loginNegative())
                .log().all()
                .post(IConfig.login).then().statusCode(400).log().all().extract().response();
        //"detail": "Incorrect email or password"
        JsonPath path=resp.jsonPath();
        assertEquals(path.getString("detail"),"Incorrect email or password");

    }
}
