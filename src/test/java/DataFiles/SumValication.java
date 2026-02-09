package DataFiles;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static javax.swing.UIManager.getInt;

public class SumValication {

    /*6. Verify if Sum of all Course prices matches with Purchase Amount*/

    @Test
    public void sumofCourse(){

       int sum=0;

       JsonPath js=new JsonPath(Payload.coursePrice());
       int count=js.getInt("courses.size()");

       for(int i=0;i<count;i++) {

           int coursePrices = js.getInt("courses[" + i + "].price");
           int copiesList = js.getInt("courses[" + i + "].copies");
           int totalAmount = coursePrices * copiesList;
           System.out.println(totalAmount);
           sum = sum + totalAmount;
       }
       System.out.println(sum);
       int purschaseAmount=js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(purschaseAmount,sum);



   }
}
