package Serialization;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;



public class AddingPlaceAPI {

    public static void main(String[] args){
        RestAssured.baseURI="https://rahulshettyacademy.com";
                PlaceInfo p=new PlaceInfo();
        p.setAccuracy(50);
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress("29, side layout, cohen 09");
        p.setWebsite("http://google.com");
        p.setLanguage("French-IN");

        //for types fields
        List<String> typeList=new ArrayList<>();
        typeList.add( "shoe park");
        typeList.add("shop");
        p.setTypes(typeList);

        //for location
        LocationAPI l=new LocationAPI();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);


        String response=given()
                .log().all()
                .queryParam("key","qaclick123")
                .body(p)
                .when()
                .get("/maps/api/place/add/json")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .header("Content-Type","application/json;charset=UTF-8")
                .extract().response().asString();
        System.out.println("RESPONSE IS..."+response);

        //JsonPath jp=new JsonPath(response);


    }
}
