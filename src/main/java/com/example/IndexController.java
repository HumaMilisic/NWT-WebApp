package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "indexNOVIdeploybranch jos da hoce povezati sa view bilo bi odlicno";
    }
}
