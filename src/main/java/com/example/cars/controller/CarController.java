package com.example.cars.controller;

import com.example.cars.domain.Car;
import com.example.cars.service.CarService;
import com.example.cars.util.Constants;
import com.example.cars.util.ResponseObject;
import com.example.cars.validations.Validations;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Controller
@RequestMapping("/api")
@Slf4j
public class CarController {

    @Autowired
    public CarService carService;

    public static final String CREATE_CAR_API = "api/create/car";

    public static final String GET_CAR_BY_BRAND_API = "api/get/car/brand";

    public static final String GET_ALL_CARS="api/get/cars";

    public static final String UPDATE_CARS ="update/car";

    public static final String DELETE_CARS="delete/car/{id}";

    private final Validations validations = new Validations();
    @PostMapping("/create/car")
    public ResponseEntity<ResponseObject> create(@RequestBody Car car) {
        ResponseObject response;
        try {
            validations.validateCar(car);
            Car result = carService.createCar(car);
            response = new ResponseObject(Constants.SUCCESS,HttpStatus.OK.name(), Constants.SUCCESS_STATUS_CODE,
                    "Created cars successfully",CREATE_CAR_API, result);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (InvalidRequestStateException e) {
           response = new ResponseObject(Constants.BAD_REQUEST,HttpStatus.BAD_REQUEST.name(),
                   Constants.BAD_REQUEST_STATUS_CODE,e.getLocalizedMessage(),CREATE_CAR_API,new HashMap<>());
           log.error("InvalidRequestException while creating the cars:{}",e.getMessage(),e);
           return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response = new ResponseObject(Constants.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    Constants.INTERNAL_SERVER_ERROR_STATUS_CODE,e.getLocalizedMessage(),CREATE_CAR_API, new HashMap<>());
            log.error("InternalServerException while creating the cars:{}",e.getLocalizedMessage(),e);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/cars")
    public ResponseEntity<ResponseObject> getAll(){
        ResponseObject response;
        try {

            List<Car> result = carService.getAllCars();
            response = new ResponseObject(Constants.SUCCESS, HttpStatus.OK.name(), Constants.SUCCESS_STATUS_CODE,
                    "List of cars fetched successfully", GET_ALL_CARS, result);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        catch (InvalidRequestStateException e) {
            response = new ResponseObject(Constants.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(),
                    Constants.BAD_REQUEST_STATUS_CODE, e.getLocalizedMessage(), GET_ALL_CARS,
                    new HashMap<>());
            log.error("InvalidRequestException occurred to import the employees for the request : {}", e.getMessage(),
                    e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            response = new ResponseObject(Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    Constants.INTERNAL_SERVER_ERROR_STATUS_CODE, e.getLocalizedMessage(), GET_ALL_CARS,
                    new HashMap<>());
            log.error("Exception occurred to import the employees for the request : {}", e.getMessage(), e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get/car/brand")
    public ResponseEntity<ResponseObject> getCarsByBrand(@RequestParam String name) {
        ResponseObject response;
        try {
            List<Car> result = carService.getCarsByBrand(name);
            response = new ResponseObject(Constants.SUCCESS, HttpStatus.OK.name(), Constants.SUCCESS_STATUS_CODE,
                    "Fetched cars by brand", GET_CAR_BY_BRAND_API, result);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (InvalidRequestStateException e) {
            response = new ResponseObject(Constants.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(),
                    Constants.BAD_REQUEST_STATUS_CODE, e.getLocalizedMessage(), GET_CAR_BY_BRAND_API,
                    new HashMap<>());
            log.error("InvalidRequest Exception occurred when fetching the cars by brand:{}", e.getMessage(), e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
           response = new ResponseObject(Constants.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.name(),
                   Constants.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), GET_CAR_BY_BRAND_API, new HashMap<>());
           log.error("Internal Server Error while fetching the cars by brand: {}", e.getMessage(),e);
           return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/car")
    public ResponseEntity<ResponseObject> update(@RequestBody Car car){
        ResponseObject response;
        try {
            validations.validateCar(car);
            Car result = carService.updateCar(car);
            response = new ResponseObject(Constants.SUCCESS,HttpStatus.OK.name(),
                    Constants.SUCCESS_STATUS_CODE,"Updated cars successfully", UPDATE_CARS,result);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (InvalidRequestStateException e) {
            response = new ResponseObject(Constants.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(),
                    Constants.BAD_REQUEST_STATUS_CODE, e.getLocalizedMessage(), UPDATE_CARS,
                    new HashMap<>());
            log.error("InvalidRequest Exception occurred while updating the cars by brand:{}", e.getMessage(), e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            response = new ResponseObject(Constants.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    Constants.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), UPDATE_CARS, new HashMap<>());
            log.error("Internal Server Error while updating the cars by brand: {}", e.getMessage(),e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/car/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable Long id){
        ResponseObject response;
        try {
            Map<String, Object> result = carService.deleteCar(id);
            response = new ResponseObject(Constants.SUCCESS,HttpStatus.OK.name(),
                    Constants.SUCCESS_STATUS_CODE,"Deleted car successfully",DELETE_CARS,result);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (InvalidRequestStateException e) {
            response = new ResponseObject(Constants.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(),
                    Constants.BAD_REQUEST_STATUS_CODE, e.getLocalizedMessage(), DELETE_CARS,
                    new HashMap<>());
            log.error("InvalidRequest Exception occurred while deleting the cars by brand:{}", e.getMessage(), e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            response = new ResponseObject(Constants.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    Constants.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), DELETE_CARS, new HashMap<>());
            log.error("Internal Server Error while deleting the cars by brand: {}", e.getMessage(),e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
