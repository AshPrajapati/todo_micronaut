package car;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.micronaut.http.HttpRequest.*;


@MicronautTest
class CarControllerTest {

  @Client
  @Inject
  HttpClient httpClient;
  @Inject
  CarTestOnlyClient carTestOnlyClient;

  WireMockServer wireMockServer;

  @BeforeEach
  void setUp() {
    wireMockServer = new WireMockServer(options().port(9090));
    wireMockServer.start();
  }

  @AfterEach
  void tearDown() {
    wireMockServer.stop();
  }

  @Test
  void get_all_cars() {
    wireMockServer.stubFor(
        get(urlEqualTo("/api/cars"))
            .willReturn(
                okJson(
                    "{\n"
                        + "    \"cars\": [\n"
                        + "        {\n"
                        + "            \"id\": 1,\n"
                        + "            \"car\": \"Mitsubishi\",\n"
                        + "            \"car_model\": \"Montero\",\n"
                        + "            \"car_color\": \"Yellow\",\n"
                        + "            \"car_model_year\": 2002,\n"
                        + "            \"car_vin\": \"SAJWJ0FF3F8321657\",\n"
                        + "            \"price\": \"$2814.46\",\n"
                        + "            \"availability\": false\n"
                        + "        },\n"
                        + "        {\n"
                        + "            \"id\": 2,\n"
                        + "            \"car\": \"Volkswagen\",\n"
                        + "            \"car_model\": \"Passat\",\n"
                        + "            \"car_color\": \"Maroon\",\n"
                        + "            \"car_model_year\": 2008,\n"
                        + "            \"car_vin\": \"WBANV9C51AC203320\",\n"
                        + "            \"price\": \"$1731.98\",\n"
                        + "            \"availability\": false\n"
                        + "        }\n"
                        + "    ]\n"
                        + "}")));
    HttpResponse<CarsWrapper> carsResponse =
        carTestOnlyClient.fetchAll();
    Assertions.assertThat(carsResponse.getStatus().getCode()).isEqualTo(200);
  }
}
