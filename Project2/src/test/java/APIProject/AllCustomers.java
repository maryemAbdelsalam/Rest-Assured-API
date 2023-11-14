package APIProject;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItems;

public class AllCustomers {

    @Test
    public void GetAllCustomers (){
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").log().
                all().when().get("api/v2/customers").then().log().all().
                assertThat().body("id",hasItems("1","2","3","4","5")).
                body("name",hasItems("Anthony Thompson","Shelley McGlynn","Jo Collier",
                        "Bobby Franecki","Ms. Sheila Witting"))
                .statusCode(200);
    }
}
