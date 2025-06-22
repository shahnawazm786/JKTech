package utils;

import com.github.javafaker.Faker;
import org.json.JSONObject;

import java.util.Random;

public class Payload {
    private Payload(){}
    public static String SignupPayload(){
        Random r= new Random();
        JSONObject object=new JSONObject();
        object.put("Id", r.nextInt(10000));
        object.put("email",new Faker().internet().emailAddress());
        object.put("password",new Faker().internet().password());
        return  object.toString();
    }
    public static String loginNegative(){
        JSONObject object=new JSONObject();
        object.put("email",new Faker().internet().emailAddress());
        object.put("password",new Faker().internet().password());
        return  object.toString();
    }
    public static String AddBook(){
        Random r= new Random();
        JSONObject object=new JSONObject();
        object.put("id",r.nextInt(50000));
        object.put("name",new Faker().book().title());
        object.put("author",new Faker().book().author());
        object.put("published_year",String.valueOf(r.nextInt(2500)));
        object.put("book_summary",new Faker().book().genre());
        return object.toString();
    }
    public static String UpdateBook(){
        JSONObject object=new JSONObject();
        object.put("name",new Faker().book().title());
        object.put("author",new Faker().book().author());
        object.put("published_year",2025);
        object.put("book_summary",new Faker().book().genre());
        return object.toString();
    }
}
