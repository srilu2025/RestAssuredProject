
import DataFiles.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicRequestPractice {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
// validate if Add Place API is working as expected


        //given - all input details
        //when - Submit the API -[resource,http method]
        //Then - validate the response
        RestAssured.baseURI= "https://rahulshettyacademy.com";

        String response=given().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(Payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope",equalTo("APP")).header("Content-Type","application/json;charset=UTF-8")
                .extract().response().asString();

        //Add place -->update place with new address-->get the place to validate if new address is present or not
        System.out.println("THE RESPONSE AFTRER ADDING THE PLACE IS"+response);

        JsonPath js=new JsonPath(response);
        String placeID=js.getString("place_id");

        System.out.println("THE GENERATED placeId is..."+placeID);

        //get place before updating of the address

        given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeID)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200);

        //update place
        String newAddress="70 Summer walk, USA";

        given().log().all().queryParam("key","qaclick123")
                .body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200);
                //.body("msg",equalTo("Address successfully updated"));




        //getting place after updating of the address
       String  gettingUpdatedResponse=given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeID)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

       JsonPath jp2=new JsonPath(gettingUpdatedResponse);
       String expectedUpdateAddress=jp2.getString("address");

        Assert.assertEquals(newAddress,expectedUpdateAddress);

        //deleting the place

        given().queryParam("key","qaclick123")
                .body("{\n" +
                        "    \"place_id\": \""+placeID
                        +"\"\n" +
                        "}")
                .when().delete("maps/api/place/delete/json")
                .then().assertThat().statusCode(200);



    }

}
