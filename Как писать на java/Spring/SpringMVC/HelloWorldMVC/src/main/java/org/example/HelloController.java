package org.example;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public String sayHello(Model model, HttpServletRequest request) {
        String param = request.getParameter("message");
        if(param != null) {
            model.addAttribute("message", param);
        } else {
            model.addAttribute("message", "");
        }
        return "hello_world";

    }
}