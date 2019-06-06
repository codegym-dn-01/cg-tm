package dn.cg.tm.cgtm.controller;

import dn.cg.tm.cgtm.dto.HomeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public HomeDTO home() {
        return new HomeDTO("Codegym Tuition Management");
    }

}
