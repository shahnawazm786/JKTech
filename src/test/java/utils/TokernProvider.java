package utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class TokernProvider {
    private TokernProvider(){}
    public static String getBrearToken(){
        RestAssured.baseURI=IConfig.BASEURI;

            Response resp = given()
                    .header("Content-type", "application/json")
                    .body(new File(IConfig.LoginFile))
                    .log().all()
                    .post(IConfig.login).then().statusCode(200).log().all().extract().response();
            JsonPath path=resp.jsonPath();
            JSONObject object=new JSONObject();
            object.put("access_token",path.getString("access_token"));
            object.put("token_type",path.getString("token_type"));
            //return object;
        return path.getString("access_token");
    }
}
