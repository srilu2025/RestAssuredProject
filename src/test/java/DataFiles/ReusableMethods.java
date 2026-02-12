package DataFiles;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

    public static JsonPath rawToJson(String response){

        JsonPath jsp=new JsonPath(response);
        return jsp;
    }
}
