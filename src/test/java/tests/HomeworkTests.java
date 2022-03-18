package tests;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

public class HomeworkTests {

    @Test
    void listUsersTest(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void createTest(){

        String data = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", CoreMatchers.is("morpheus"))
                .body("job", CoreMatchers.is("leader"));
    }

    @Test
    void updateTest(){
        String data = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", CoreMatchers.is("morpheus"))
                .body("job", CoreMatchers.is("zion resident"));
    }

    @Test
    void patchTest(){
        String data = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", CoreMatchers.is("morpheus"))
                .body("job", CoreMatchers.is("zion resident"));
    }

    @Test
    void deleteTest(){
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

}
