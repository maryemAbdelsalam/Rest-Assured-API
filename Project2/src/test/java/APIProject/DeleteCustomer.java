package APIProject;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class DeleteCustomer {

    @Test(dependsOnMethods = {"GetPARTIALLYUpdatedCustomer"})
    public void DeleteCustomer (){
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").
                log().all().when().delete("api/v2/customers/6").then().log().all().
                assertThat().statusCode(200);
    }
}
