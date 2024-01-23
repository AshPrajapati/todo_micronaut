package car;

import io.micronaut.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarControllerShould {
  @Mock CarService carService;
  @InjectMocks CarController carController;

  @Test
  void get_all_todos(){
    HttpResponse<CarsWrapper> carsWrapper = carController.getAllCars();
    verify(carService).getAllCars();
  }
}
