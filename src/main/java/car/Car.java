package car;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    private String id;
    private String carMaker;
    private int year;
    private String model;

    public Car(
            @JsonProperty("id") String id,
            @JsonProperty("car") String carMaker,
            @JsonProperty("car_model_year") int year,
            @JsonProperty("car_model") String model) {
        this.id = id;
        this.carMaker = carMaker;
        this.year = year;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public String getCarMaker() {
        return carMaker;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }
}
