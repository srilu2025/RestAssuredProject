package ActualClassFiles;

import DataFiles.Payload;
import io.restassured.path.json.JsonPath;

/**1. Print No of courses returned by API

 2.Print Purchase Amount

 3. Print Title of the first course

 4. Print All course titles and their respective Prices

 5. Print no of copies sold by RPA Course

 6. Verify if Sum of all Course prices matches with Purchase Amount*/

public class ComplexJsonParsingClass {

    public static void main(String[] args){

        /*1. Print No of courses returned by API*/
        JsonPath js=new JsonPath(Payload.coursePrice());

        int noOfCourses=js.getInt("courses.size()");

        System.out.println("NO OF COURSES IN THE LIST ARE..."+noOfCourses);

        /*2.Print Purchase Amount*/

        int purschaseAmount=js.getInt("dashboard.purchaseAmount");

        System.out.println("THE PURCHASE AMOUNT IS...."+purschaseAmount);

        /*3. Print Title of the first course*/

        String courseTitle=js.getString("courses[0].title");

        System.out.println("THE NAME OF THE COURSE IS..."+courseTitle);

        /*4. Print All course titles and their respective Prices*/

        for(int i=0;i<noOfCourses;i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            int prices = js.get("courses[" + i + "].price");
            System.out.println(courseTitles + prices);

        }

        /*5. Print no of copies sold by RPA Course*/

        for(int j=0;j<noOfCourses;j++){

            String coursesTitles=js.get("courses["+j+"].title");
            if(coursesTitles.equalsIgnoreCase("RPA")){
               int copiesCount= js.get("courses["+j+"].copies");
                System.out.println(coursesTitles +" "+copiesCount);
                break;
            }
        }


    }
}


