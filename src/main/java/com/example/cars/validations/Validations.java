package com.example.cars.validations;


import com.example.cars.domain.Car;
import com.example.cars.util.InvalidRequestException;

import java.math.BigDecimal;

public class Validations {

    public void validateCar(Car car){

        String regexEnginePower = "^(?!-)(\\d+(\\.\\d+)?\\s*(kW|hp)?)$";

        String regexMileage = "^(?!-)(\\d+(\\.\\d+)?\\s*(km/l|km/h|miles/gallon|miles/h)?)$";

        // Validate enginePower
        if (car.getEnginePower() == null || !car.getEnginePower().matches(regexEnginePower)) {
            throw new InvalidRequestException("EnginePower must be valid and not be empty");
        }

        // Validate price
        if (car.getPrice() == null || car.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidRequestException("Price must be valid and not be empty");
        }

        // Validate mileage
        if (car.getMileage() == null || !car.getMileage().matches(regexMileage)) {
            throw new InvalidRequestException("Mileage must be valid and not be empty");
        }
    }
}
