package APIProject;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class GetDeleted {

    @Test(dependsOnMethods = {"DeleteCustomer"})
    public void GetDeletedCustomer (){
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").
                queryParam("id","6").log().
                all().when().get("api/v2/customers").then().log().all().
                assertThat().statusCode(200);
    }
}
