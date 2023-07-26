package com.example.cars.service;


import com.example.cars.domain.Brand;
import com.example.cars.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BrandService {

    @Autowired
    public BrandRepository brandRepository;
    public Brand createBrand(Brand brand) {
       return brandRepository.save(brand);
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Long id){
        return brandRepository.findById(id);
    }

    public Brand updateBrand(Brand brand) {
        Brand brands = brandRepository.findById(brand.getId()).orElse(null);
        brands.setId(brand.getId());
        brands.setName(brand.getName());
        brands.setEstablishedYear(brand.getEstablishedYear());
        return brandRepository.save(brands);
    }

    public Map<String, Object> deleteBrand(Long id) {
        Map<String,Object> brand = new HashMap<>();
        Optional<Brand> deleteBrand = brandRepository.findById(id);
        if (deleteBrand.isPresent()) {
            brandRepository.deleteById(id);
        }
        return brand;
    }

}
