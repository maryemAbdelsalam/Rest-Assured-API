package APIProject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CreateCustomer {

    @Test(dependsOnMethods = {"GetSecondCustomer"})
    public void CreateNewCustomer(){
        String body = "{\n" +
                "        \"name\": \"Maryem Abdelsalam\",\n" +
                "        \"email\": \"Maryem@yahoo.com\",\n" +
                "        \"mobile\": \"01234567890\",\n" +
                "        \"city\": \"Cairo\",\n" +
                "        \"product\": \"Oriental Bronze Ball\",\n" +
                "        \"amount\": \"12\",\n" +
                "        \"id\": \"6\"\n" +
                "    }";
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").
                contentType(ContentType.JSON).body(body).log().all().
                when().post("api/v2/customers").
                then().log().all().statusCode(201);
    }
}
