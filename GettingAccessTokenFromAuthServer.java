package OAuthPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class GettingAccessTokenFromAuthServer {

    public static  String accessToken;

    public static String getAccessToken(){


        RestAssured.baseURI="https://rahulshettyacademy.com/";

       String response= given().log().all()
                .formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust")
                .when()
                .post("oauthapi/oauth2/resourceOwner/token")
                .then().log().all()
                .assertThat()
                .statusCode(200).extract().response().asString();

       //parsing the JSOn response
        JsonPath js=new JsonPath(response);
        accessToken= js.get("access_token");
        System.out.println("TOKEN IS..."+accessToken);

        return accessToken;

    }
}
