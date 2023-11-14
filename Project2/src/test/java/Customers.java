import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class Customers {

    @Test
    public void GetAllCustomers (){
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").log().
                all().when().get("api/v2/customers").then().log().all().
                assertThat().body("id",hasItems("1","2","3","4","5")).
                body("name",hasItems("Anthony Thompson","Shelley McGlynn","Jo Collier",
                        "Bobby Franecki","Ms. Sheila Witting"))
                .statusCode(200);
    }
    @Test
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
    @Test (dependsOnMethods = {"GetSecondCustomer"})
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
    @Test (dependsOnMethods = {"CreateNewCustomer"})
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
    @Test (dependsOnMethods = {"GetTheNewCustomer"})
    public void UpdateNewCustomer(){
        String body = "{\n" +
                "        \"name\": \"Maryem Abdelsalam Abdelrahman\",\n" +
                "        \"email\": \"Maryem@yahoo.com\",\n" +
                "        \"mobile\": \"01234567890\",\n" +
                "        \"city\": \"Cairo\",\n" +
                "        \"product\": \"Oriental Bronze Ball\",\n" +
                "        \"amount\": \"12\",\n" +
                "        \"id\": \"6\"\n" +
                "    }";
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").
                contentType(ContentType.JSON).body(body).log().all().
                when().put("api/v2/customers/6").
                then().log().all().statusCode(200);
    }
    @Test (dependsOnMethods = {"UpdateNewCustomer"})
    public void GetTheUpdatedCustomer (){
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").
                queryParam("id","6").log().
                all().when().get("api/v2/customers").then().log().all().
                assertThat().body("name",hasItem("Maryem Abdelsalam Abdelrahman")).
                body("email",hasItem("Maryem@yahoo.com")).
                body("mobile",hasItem("01234567890")).
                body("city",hasItem("Cairo")).body("product",hasItem("Oriental Bronze Ball")).
                body("amount",hasItem("12")).body("id",hasItem("6")).
                statusCode(200);
    }
    @Test (dependsOnMethods = {"GetTheUpdatedCustomer"})
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
    @Test (dependsOnMethods = {"PartialUpdateCustomer"})
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
    @Test (dependsOnMethods = {"GetPARTIALLYUpdatedCustomer"})
    public void DeleteCustomer (){
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").
                log().all().when().delete("api/v2/customers/6").then().log().all().
                assertThat().statusCode(200);
    }
    @Test (dependsOnMethods = {"DeleteCustomer"})
    public void GetDeletedCustomer (){
        RestAssured.given().baseUri("https://654a98131f197d51e4927399.mockapi.io/").
                queryParam("id","6").log().
                all().when().get("api/v2/customers").then().log().all().
                assertThat().statusCode(200);
    }
}
