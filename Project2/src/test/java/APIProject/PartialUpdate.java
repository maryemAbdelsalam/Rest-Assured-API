package APIProject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class PartialUpdate {

    @Test(dependsOnMethods = {"GetTheUpdatedCustomer"})
    public void PartialUpdateCustomer(){
        String body = "{\n" +
                "        \"email\": \"Maryem@gmail.com\",\n" +
                "        \"city\": \"Alexandria\"\n" +
                "}";
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").
                contentType(ContentType.JSON).body(body).log().all().
                when().patch("api/v2/customers/6").
                then().log().all().statusCode(200);
    }
}
