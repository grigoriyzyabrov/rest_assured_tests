package tests;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class DemowebshopHomeworkTests {

    @Test
    void addToWishlist(){
        Integer size = 14;
        ValidatableResponse response =
       given()
               .contentType("application/x-www-form-urlencoded; charset=UTF-8")
               .cookie("ARRAffinity=a1e87db3fa424e3b31370c78def315779c40ca259e77568bef2bb9544f63134e;" +
                       " Nop.customer=21c7cd00-d16c-47ce-aa6f-8fccb40c3d30;")
               .body("addtocart_18.EnteredQuantity=1&addtocart_19.EnteredQuantity=1")
               .when()
               .post("http://demowebshop.tricentis.com/addproducttocart/details/18/2")
               .then()
               .log().all()
               .statusCode(200)
               .body("success", is(true))
               .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
               .body("updatetopwishlistsectionhtml", is("("+size+")"));

    }
    @Test
    void addToWishListTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("addtocart_14.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/14/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your " +
                        "<a href=\"/wishlist\">wishlist</a>"))
                .body("updatetopwishlistsectionhtml", is("(1)"));
    }
}
