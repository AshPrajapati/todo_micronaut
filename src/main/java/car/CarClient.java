package car;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client(value="${carsApi.hostUrl}")
public interface CarClient {
  @Get("/api/cars")
  CarsWrapper fetchAll();
}
