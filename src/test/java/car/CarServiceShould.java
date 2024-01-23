package car;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarServiceShould {
    @Mock CarClient carClient;
    @InjectMocks CarService carService;
    @Test
    void get_all_cars(){
        CarsWrapper carsWrapper = carService.getAllCars();
        Mockito.verify(carClient).fetchAll();
    }
}
