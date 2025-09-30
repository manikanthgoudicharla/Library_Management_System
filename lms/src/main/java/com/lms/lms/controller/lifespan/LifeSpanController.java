package com.lms.lms.controller.lifespan;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lifespan")
public class LifeSpanController {

    @GetMapping
    public String getLifeSpanCheck(){
        return "LifeSpan is Healthy";
    } 
    
}
