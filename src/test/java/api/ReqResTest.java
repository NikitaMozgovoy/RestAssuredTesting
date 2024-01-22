package api;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqResTest {
    private final static String URL ="https://reqres.in/";


    // Проверка сопадения id пользователя и значения id в названии аватара,
    // а также принадлжености адресов электронной почты пользователей к серверу reqres.in
    @Test
    public void checkAvatarAndEmailTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.stream().forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
    }


    // Позитиивный тест регистрации пользователя
    @Test
    public void successRegTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register/")
                .then().log().all()
                .extract().as(SuccessReg.class);
        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());

        Assert.assertEquals(id, successReg.getId());
        Assert.assertEquals(token, successReg.getToken());

    }

    // Негативный тест регистрации пользователя
    @Test
    public void unSuccessRegTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecErr400());
        Register user = new Register("sydney@fife", "");
        UnsuccessfulReg unsuccessReg = given()
                .body(user)
                .when()
                .post("api/register/")
                .then().log().all()
                .extract().as(UnsuccessfulReg.class);

        Assert.assertEquals("Missing password", unsuccessReg.getError());
    }

    // Проверка упорядоченности цветов  по годам в ответе
    @Test
    public void sortedYearsTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<ColorsData> colors  = given()
                .when()
                .get("api/unknown")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(years, sortedYears);
    }

    // Позитивный тест удаления пользователя, используется спецификация ответа с устанавливаемым кодом
    @Test
    public void deleteUserTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(204));
        given().when().delete("api/users/2").then().log().all();
    }
}
