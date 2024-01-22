package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/** Класс со спецификациями запросов и ответов, использующихся для уменьшения повторяемости кода */
public class Specifications {

    // Спецификация запроса, устанавливает базовый адрес и тип контента
    public static RequestSpecification requestSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    // Спецификация успешного ответа с кодом 200
    public static ResponseSpecification responseSpecOK200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    // Спецификация ответа с кодом 400
    public static ResponseSpecification responseSpecErr400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    // Спецификация ответа с определенным кодом ответа
    public static ResponseSpecification responseSpecUnique(int status){
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    // Сеттер глобальных спецификаций запроса и ответа, используется в каждом тесте с разными спецификациями
    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
