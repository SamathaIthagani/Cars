package com.example.cars.service;

import com.example.cars.controller.BrandController;
import com.example.cars.domain.Brand;
import com.example.cars.domain.Car;
import com.example.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    public CarRepository carRepository;

    @Autowired
    public BrandController brandController;

    public Car createCar(Car car) {

        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car updateCar(Car car) {
        Car car1 = carRepository.findById(car.getId()).orElse(null);
        car1.setId(car.getId());
        car1.setBrand(car.getBrand());
        car1.setFuelType(car.getFuelType());
        car1.setMileage(car.getMileage());
        car1.setPrice(car.getPrice());
        car1.setEnginePower(car.getEnginePower());
        return carRepository.save(car1);
    }

    public String deleteCar(Long id) {
        carRepository.deleteById(id);
        return " This " + id + " car was deleted";
    }

    public List<Car> getCarsByBrand(String name) {
        return carRepository.findCarsByBrand(name);
    }
}




