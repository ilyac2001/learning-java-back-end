package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hi")
    public String FirstHello(HttpServletRequest request){
        String name = request.getParameter("name");
        System.out.println(name + " " + request.getRequestURI());
        return "first/hello";
    }

    @GetMapping("/bye")
    public String FirstGoodbye(@RequestParam(value = "name", required = false) String name) {
        System.out.println(name);
        return "first/goodbye";
    }
}
