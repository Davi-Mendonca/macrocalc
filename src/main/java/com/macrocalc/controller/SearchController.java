package com.macrocalc.controller;

import com.google.gson.JsonObject;
import com.macrocalc.model.AccessResponse;
import com.macrocalc.service.SearchFoodsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.http.HttpResponse;

@Controller(value = "/teste")
public class SearchController {

    @Autowired
    SearchFoodsService service;

    @GetMapping(value = "/token")
    public @ResponseBody String getToken() throws IOException, InterruptedException {

        return service.getAccesstoken();
    }

    @GetMapping(value = "/searchfood")
    public @ResponseBody String searchFood(@RequestParam String foodName) throws IOException {
        return service.getFood(foodName);
    }
}
