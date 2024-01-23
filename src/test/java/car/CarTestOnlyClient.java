package car;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("/cars")
public interface CarTestOnlyClient {
    @Get
    HttpResponse<CarsWrapper> fetchAll();
}
