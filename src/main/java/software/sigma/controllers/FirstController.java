package software.sigma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("hello")
    public String helloPage(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        model.addAttribute("message", "Hello, " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("goodbye")
    public String goodByePage(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname) {
        System.out.println("Goodbye, " + name + " " + surname);

        return "first/goodbye";
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam("firstParam") int firstParam,
                            @RequestParam("secondParam") int secondParam,
                            @RequestParam("action") String action,
                            Model model) {
        double result;

        switch (action) {
            case "multiplicate":
                result = firstParam * secondParam;
                break;
            case "division":
                result = firstParam / (double)secondParam;
                break;
            case "subtraction":
                result = firstParam - secondParam;
                break;
            case "addition":
                result = firstParam + secondParam;
                break;
            default:
                result = 0;
                break;
        }

        model.addAttribute("result", result);

        return "first/calculate";
    }
}
