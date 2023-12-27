package org.example.kirana_register_project.crud.controller;


import org.example.kirana_register_project.crud.service.FxRatesService;
import org.example.kirana_register_project.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class FxRatesController {

    @Autowired
    private FxRatesService fxRatesService;

    @GetMapping("/fx-rates")
    public Map<String, Double> getFxRates(){
        return fxRatesService.getFxRates();
    }
}
