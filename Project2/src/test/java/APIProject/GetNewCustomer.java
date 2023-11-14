package APIProject;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItem;

public class GetNewCustomer {

    @Test(dependsOnMethods = {"CreateNewCustomer"})
    public void GetTheNewCustomer (){
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").
                queryParam("id","6").log().
                all().when().get("api/v2/customers").then().log().all().
                assertThat().body("name",hasItem("Maryem Abdelsalam")).
                body("email",hasItem("Maryem@yahoo.com")).
                body("mobile",hasItem("01234567890")).
                body("city",hasItem("Cairo")).body("product",hasItem("Oriental Bronze Ball")).
                body("amount",hasItem("12")).body("id",hasItem("6")).
                statusCode(200);
    }
}
