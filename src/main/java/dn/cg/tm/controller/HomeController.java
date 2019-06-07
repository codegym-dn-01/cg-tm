package dn.cg.tm.controller;

import dn.cg.tm.dto.HomeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public HomeDTO home() {
        return new HomeDTO("Codegym Tuition Management");
    }

}
