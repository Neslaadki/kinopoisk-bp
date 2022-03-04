package com.example.kinopoiskbmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kinopoisk")
@RequiredArgsConstructor
public class WebController {

    @GetMapping("/getContentTypes")
    public void getContentTypes(){

    }

    @GetMapping("/getContentGenre")
    public void getContentGenre(){

    }

    


}
