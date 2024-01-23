package car;

import jakarta.inject.Singleton;

@Singleton
public class CarService {
  private final CarClient carClient;

    public CarService(CarClient carClient) {
        this.carClient = carClient;
    }

    public CarsWrapper getAllCars() {
        CarsWrapper carsWrapper = carClient.fetchAll();
        return carsWrapper;
    }
}
