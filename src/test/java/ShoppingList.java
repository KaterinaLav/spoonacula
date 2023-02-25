import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ShoppingList extends Abstract {
    @Test
    void getConnectUser() {
        given()
                .queryParam("username", "LavruhinaEA@yandex.ru")
                .queryParam("hash", "Deti151719")
                .pathParam("id", 716429)
                .when()
                .then()
                .statusCode(200);
    }
    @Test
    void generateMealPlanet() {
        given()
                .queryParam("timeFrame", "day")
                .queryParam("targetCalories", "2000")
                .queryParam("diet", "vegetarian")
                .queryParam("exclude", "shellfish")
                .body("username", ObjectMapperType.valueOf("LavruhinaEA@yandex.ru"))
                .when()
                .post(getBaseUrl()+"mealplanner/{username}/items")
                .then()
                .statusCode(200);
    }
    @Test
    void templareMealPlanet() {
        given()
                .queryParam("timeFrame", "day")
                .queryParam("targetCalories", "2000")
                .queryParam("diet", "vegetarian")
                .queryParam("exclude", "shellfish")
                .pathParam("username", "LavruhinaEA@yandex.ru")
                .pathParam("id", "01")
                .queryParam("hash", "Deti151719")
                .when()
                .get(getBaseUrl()+"/mealplanner/{username}/templates/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    void addShoppingList(){
        given()
                .pathParam("username", "LavruhinaEA@yandex.ru")
                .queryParam("hash", "Deti151719")
                .when()
                .post(getBaseUrl()+"/mealplanner/{username}/shopping-list/items")
                .then()
                .statusCode(200);
    }

    @BeforeAll
    void deleteMealPlanet(){
        given()
                .pathParam("username", "LavruhinaEA@yandex.ru")
                .pathParam("id", "01")
                .queryParam("hash", "Deti151719")
                .when()
                .delete(getBaseUrl()+"/mealplanner/{username}/templates/{id}")
                .then()
                .statusCode(200);
    }
}
