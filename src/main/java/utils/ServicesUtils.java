package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.util.Objects;

import static io.restassured.RestAssured.given;

public class ServicesUtils {

  public enum HttpMethod {
    GET("get"), POST("post");
    @Getter
    private final String method;

    HttpMethod(String method) {
      this.method = method;
    }
  }

  private static final RequestSpecification request = given().accept(ContentType.JSON);

  public static Response execute(String endpoint, HttpMethod method) {
    return execute(endpoint, method, true);
  }

  private static Response execute(String endpoint, HttpMethod method, boolean verifyStatusCode) {
    Response response = null;
    if (method == HttpMethod.GET) {
      response = request.get(endpoint);
    } else if (method == HttpMethod.POST) {
      response = request.post(endpoint);
    }
    assert response != null;
    Objects.requireNonNull(response).then().log().all();
    if (verifyStatusCode) {
      response.then().statusCode(200);
    }
    return response;
  }
}