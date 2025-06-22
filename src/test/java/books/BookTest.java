package books;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.IConfig;
import utils.Payload;
import utils.TokernProvider;

import java.util.List;
import java.util.Map;
import java.util.Random;
import static io.restassured.RestAssured.given;

@Listeners(listeners.ExtentReportListener.class)
public class BookTest {
    @BeforeMethod
    public void setup(){
        RestAssured.baseURI= IConfig.BASEURI;
    }
    @Test(description = "Token not provided")
    public void Test_Add_Book_Negative(){
        Response resp = given()
                .header("Content-type", "application/json")
                .body(Payload.AddBook())
                .log().all()
                .post(IConfig.NewBOOK)
                .then()
                .statusCode(403)
                .log()
                .all()
                .extract()
                .response();

    }
    @Test(description = "Add new books")
    public void Test_Add_Book(){
        Response resp = given()
                .header("Authorization", "Bearer " + TokernProvider.getBrearToken())
                .header("Content-type", "application/json")
                .body(Payload.AddBook())
                .log().all()
                .post(IConfig.NewBOOK)
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .response();
    }


    @Test(description = "Delete book")
    public void Test_Delete_Book(){
        String token=TokernProvider.getBrearToken();
        Response resp = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(Payload.AddBook()).post(IConfig.NewBOOK).then().extract().response();
        JsonPath path=resp.jsonPath();
        String book_id=path.getString("id");
        Response delete = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .when()
                .delete(IConfig.deleteBook+"/"+book_id)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Test
    public void Test_Delete_Book_Not_Found(){
        String token=TokernProvider.getBrearToken();
        Response delete = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .when()
                .delete(IConfig.deleteBook+"/"+String.valueOf(new Random().nextInt(3000)))
                .then()
                .log().all()
                .statusCode(404)
                .extract()
                .response();
    }

    @Test
    public void Test_Get_Book(){
        String token=TokernProvider.getBrearToken();
        Response resp = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .get(IConfig.GetBooks).
                then()
                .statusCode(200)
                .extract().response();
        JsonPath path=resp.jsonPath();
        List<Map<String, Object>> books = path.getList("$");
        for(Map<String,Object> book:books){
            System.out.println("ID: " + book.get("id"));
            System.out.println("Name: " + book.get("name"));
            System.out.println("Author: " + book.get("author"));
            System.out.println("Published Year: " + book.get("published_year"));
            System.out.println("Summary: " + book.get("book_summary"));
        }
    }

    @Test
    public void Test_Get_Book_By_ID() {
        String token = TokernProvider.getBrearToken();
        Response resp = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .get(IConfig.GetBooks+"/3").
                then()
                .statusCode(200)
                .extract().response();
        System.out.println(resp.asPrettyString());
    }
    @Test
    public void Test_Update_Book() {
        String token = TokernProvider.getBrearToken();

        Response resp = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(Payload.UpdateBook())
                .put(IConfig.updateBook+"/3").
                then()
                .log().all()
                .statusCode(200)
                .extract().response();
        System.out.println(resp.asPrettyString());
    }
    @Test
    public void Test_Update_Book_No_Found() {
        String token = TokernProvider.getBrearToken();

        Response resp = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(Payload.UpdateBook())
                .put(IConfig.updateBook+"/34").
                then()
                .log().all()
                .statusCode(404)
                .extract().response();
        System.out.println(resp.asPrettyString());
    }

}
