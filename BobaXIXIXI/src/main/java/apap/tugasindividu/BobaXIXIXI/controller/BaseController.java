package apap.tugasindividu.BobaXIXIXI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
    private String home() { return "home"; }
}
