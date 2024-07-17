package org.example.controllers;

import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

interface Operation{
    double execute(double x, double y);
}

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String Calculate(@RequestParam("operand1") String op1,
                            @RequestParam("operand2") String op2,
                            @RequestParam("action") String action,
                            Model model){
        try {
            Operation operation = Action(action);
            Double result = operation.execute(Double.parseDouble(op1), Double.parseDouble(op2));
            model.addAttribute("result", result);

        } catch (Exception e) {
            model.addAttribute("result", e.getMessage());
        }

        return "/calculator";
    }

    private static Operation Action(String action) throws Exception{
        switch (action){
            case "multiplication": return (x, y) -> x * y;
            case "addition":       return (x, y) -> x + y;
            case "subtraction":    return (x, y) -> x - y;
            case "division":       return (x, y) -> x / y;
            default:
                throw new Exception("no correct action");
        }
    }
}
