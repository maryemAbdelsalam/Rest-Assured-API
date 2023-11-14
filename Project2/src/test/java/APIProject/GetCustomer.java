package APIProject;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

public class GetCustomer {

    @Test (dependsOnMethods = {"GetAllCustomers"})
    public void GetSecondCustomer (){
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").
                queryParam("city","Columbia").log().
                all().when().get("api/v2/customers").then().log().all().
                assertThat().body("name",hasItems("Shelley McGlynn")).
                body("email",hasItem("Clark.Dickens@gmail.com")).
                body("mobile",hasItem("446.890.7558 x51411")).
                body("product",hasItem("Handcrafted Soft Tuna")).
                body("amount",hasItem("606.92")).
                body("id",hasItem("2"))
                .statusCode(200);
    }
}
