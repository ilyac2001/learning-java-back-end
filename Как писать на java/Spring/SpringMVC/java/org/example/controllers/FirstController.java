package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hi")
    public String FirstHello(){
        return "first/hello";
    }

    @GetMapping("/bye")
    public String FirstGoodbye() {
        return "first/goodbye";
    }
}
