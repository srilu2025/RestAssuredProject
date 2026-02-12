package BookAPI;

import DataFiles.Payload;
import DataFiles.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddingBook {

    @Test
    public void addBook(){

        RestAssured.baseURI="http://216.10.245.166";

        String response=given().log().all().body(Payload.addBookPayload()).
                when().post("Library/Addbook.php").
                  then().log().all().assertThat().statusCode(200).extract().response().asString();


        JsonPath jsonResponse=ReusableMethods.rawToJson(response);
        String id=jsonResponse.get("ID");
        System.out.println(id);
    }
}
