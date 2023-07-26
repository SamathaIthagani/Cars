package com.example.cars.controller;

import com.example.cars.domain.Brand;
import com.example.cars.service.BrandService;
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
public class BrandController {

    public static final String CREATE_BRAND = "/create";

    public static final String GET_ALL_BRANDS= "/get/brands";

    public static final String GET_BRAND_BY_ID= "/get/brand/{id}";

    public static final String UPDATE_BRAND= "/update";

    public static final String DELETE_BRAND= "/delete/{id}";
    @Autowired
    public BrandService brandService;

    private final Validations validations = new Validations();

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> create(@RequestBody Brand brand) {
        ResponseObject response;
     try{
         validations.validateBrand(brand);
         Brand result = brandService.createBrand(brand);
        response = new ResponseObject(Constants.SUCCESS, HttpStatus.OK.name(), Constants.SUCCESS_STATUS_CODE,
                "Created brands successfully", CREATE_BRAND, result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
     catch(InvalidRequestStateException e){
         response = new ResponseObject(Constants.BAD_REQUEST,HttpStatus.BAD_REQUEST.name(),
                 Constants.BAD_REQUEST_STATUS_CODE,e.getLocalizedMessage(),CREATE_BRAND,new HashMap<>());
         log.error("InvalidRequestException while creating the brands:{}",e.getMessage(),e);
         return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
     }
     catch(Exception e) {
         response = new ResponseObject(Constants.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.name(),
                 Constants.INTERNAL_SERVER_ERROR_STATUS_CODE,e.getLocalizedMessage(),CREATE_BRAND, new HashMap<>());
         log.error("InternalServerException while creating the brands:{}",e.getLocalizedMessage(),e);
         return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }

    @GetMapping("/get/brands")
    public ResponseEntity<ResponseObject> getAll() {
        ResponseObject response;
        try {
            List<Brand> result = brandService.getAllBrands();
            response = new ResponseObject(Constants.SUCCESS,HttpStatus.OK.name(), Constants.SUCCESS_STATUS_CODE,
                    "fetched all brands successfully",GET_ALL_BRANDS, result);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch(InvalidRequestStateException e){
            response = new ResponseObject(Constants.BAD_REQUEST,HttpStatus.BAD_REQUEST.name(),
                    Constants.BAD_REQUEST_STATUS_CODE,e.getLocalizedMessage(),GET_ALL_BRANDS,new HashMap<>());
            log.error("InvalidRequestException while fetching the brands:{}",e.getMessage(),e);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        catch(Exception e) {
            response = new ResponseObject(Constants.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    Constants.INTERNAL_SERVER_ERROR_STATUS_CODE,e.getLocalizedMessage(),GET_ALL_BRANDS, new HashMap<>());
            log.error("InternalServerException while fetching the brands:{}",e.getLocalizedMessage(),e);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get/brand/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Long id) {
        ResponseObject response;
        try {
            Optional<Brand> result = brandService.getBrandById(id);
            response = new ResponseObject(Constants.SUCCESS,HttpStatus.OK.name(), Constants.SUCCESS_STATUS_CODE,
                    "fetched the brand successfully",GET_BRAND_BY_ID, result);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch(InvalidRequestStateException e){
            response = new ResponseObject(Constants.BAD_REQUEST,HttpStatus.BAD_REQUEST.name(),
                    Constants.BAD_REQUEST_STATUS_CODE,e.getLocalizedMessage(),GET_BRAND_BY_ID,new HashMap<>());
            log.error("InvalidRequestException while fetching the brand:{}",e.getMessage(),e);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        catch(Exception e) {
            response = new ResponseObject(Constants.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    Constants.INTERNAL_SERVER_ERROR_STATUS_CODE,e.getLocalizedMessage(),GET_BRAND_BY_ID, new HashMap<>());
            log.error("InternalServerException while fetching the brand:{}",e.getLocalizedMessage(),e);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateBrand(@RequestBody Brand brand) {
        ResponseObject response;
        Brand result = brandService.updateBrand(brand);
        try {
            validations.validateBrand(brand);
            response = new ResponseObject(Constants.SUCCESS, HttpStatus.OK.name(), Constants.SUCCESS_STATUS_CODE,
                    "Updated the brand successfully", UPDATE_BRAND, result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(InvalidRequestStateException e){
            response = new ResponseObject(Constants.BAD_REQUEST,HttpStatus.BAD_REQUEST.name(),
                    Constants.BAD_REQUEST_STATUS_CODE,e.getLocalizedMessage(),UPDATE_BRAND,new HashMap<>());
            log.error("InvalidRequestException while updating the brand:{}",e.getMessage(),e);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        catch(Exception e) {
            response = new ResponseObject(Constants.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    Constants.INTERNAL_SERVER_ERROR_STATUS_CODE,e.getLocalizedMessage(),UPDATE_BRAND, new HashMap<>());
            log.error("InternalServerException while updating the brand:{}",e.getLocalizedMessage(),e);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteBrand(@PathVariable Long id) {
        ResponseObject response;
        try {
            Map<String, Object> result = brandService.deleteBrand(id);
                response = new ResponseObject(Constants.SUCCESS, HttpStatus.OK.name(), Constants.SUCCESS_STATUS_CODE,
                        "Deleted the brand successfully", DELETE_BRAND, result);
                return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (InvalidRequestStateException e) {
            response = new ResponseObject(Constants.BAD_REQUEST,HttpStatus.BAD_REQUEST.name(),
                    Constants.BAD_REQUEST_STATUS_CODE,e.getLocalizedMessage(),UPDATE_BRAND,new HashMap<>());
            log.error("InvalidRequestException while deleting the brand:{}",e.getMessage(),e);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            response = new ResponseObject(Constants.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    Constants.INTERNAL_SERVER_ERROR_STATUS_CODE,e.getLocalizedMessage(),UPDATE_BRAND, new HashMap<>());
            log.error("InternalServerException while deleting the brand:{}",e.getLocalizedMessage(),e);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    }

