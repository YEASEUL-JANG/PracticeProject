package com.example.catalogservice.controller;

import com.example.catalogservice.dto.ResponseCatalog;
import com.example.catalogservice.entity.CatalogEntity;
import com.example.catalogservice.service.CatalogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {
    private Environment env;
    private CatalogService catalogService;
    @Autowired
    public CatalogController(Environment env, CatalogService catalogService) {
        this.env = env;
        this.catalogService = catalogService;
    }

    @GetMapping("/health_check")
    public String status(HttpServletRequest request){
        return String.format("It's Working in Catalog Service on Port %s", request.getServerPort());

    }
    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs(){
        Iterable<CatalogEntity> orderList = catalogService.getAllCatalogs();
        List<ResponseCatalog> result = new ArrayList<>();
        orderList.forEach(v-> {
            result.add(new ModelMapper().map(v, ResponseCatalog.class));
        });
        return ResponseEntity.ok(result);
    }


}
