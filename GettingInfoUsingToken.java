package OAuthPractice;

import PojoPractice.GetCourse;
import PojoPractice.Course;
import io.restassured.RestAssured;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class GettingInfoUsingToken {

    public static void main(String[] args) {


        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        String token=GettingAccessTokenFromAuthServer.getAccessToken();
        String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};


       String response= given()
                .queryParam("access_token",token)
                .when()
                .get("oauthapi/getCourseDetails")
                .then()
                .assertThat().statusCode(401).extract().response().asString();

        System.out.println(response);

        //Here I am also testing the pojo practice

        GetCourse gc=given().log().all()
                .queryParam("access_token",token)
                .when()
                .get("oauthapi/getCourseDetails")
                .then().log().all()
                .assertThat().statusCode(401).extract().response().as(GetCourse.class);


        System.out.println("INSTRUCTOR NAME IS..."+gc.getInstructor());


        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

        List<Course> apiCourses = gc.getCourses().getApi();
        for (int i = 0; i < apiCourses.size(); i++) {
            if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

// Get the course names of WebAutomation
        ArrayList<String> actualTitles = new ArrayList<>();

        List<Course> webCourses = gc.getCourses().getWebAutomation();
        for (int j = 0; j < webCourses.size(); j++) {
            actualTitles.add(webCourses.get(j).getCourseTitle());
        }

        List<String> expectedList = Arrays.asList(courseTitles);
        Assert.assertEquals(actualTitles, expectedList);














    }
}