package APIProject;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItem;

public class GetPartialUpdated {

    @Test(dependsOnMethods = {"PartialUpdateCustomer"})
    public void GetPARTIALLYUpdatedCustomer (){
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").
                queryParam("id","6").log().
                all().when().get("api/v2/customers").then().log().all().
                assertThat().body("name",hasItem("Maryem Abdelsalam Abdelrahman")).
                body("email",hasItem("Maryem@gmail.com")).
                body("mobile",hasItem("01234567890")).
                body("city",hasItem("Alexandria")).body("product",hasItem("Oriental Bronze Ball")).
                body("amount",hasItem("12")).body("id",hasItem("6")).
                statusCode(200);
    }
}
