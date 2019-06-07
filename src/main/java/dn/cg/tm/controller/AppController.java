package dn.cg.tm.controller;

import dn.cg.tm.domain.User;
import dn.cg.tm.repository.UserRepository;
import dn.cg.tm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/test")
    public ResponseEntity<?> sayHello() {
        return new ResponseEntity<>("Welcome to my website", HttpStatus.OK);
    }
    @GetMapping("/admin/test")
    public ResponseEntity<?> sayAdmin() {
        return new ResponseEntity<>("Welcome admin to my website", HttpStatus.OK);
    }
    @GetMapping("/user/test")
    public ResponseEntity<?> sayUser() {
        return new ResponseEntity<>("Welcome user to my website", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken=jwtTokenProvider.generateToken(authentication);
        return new ResponseEntity<>( jwtToken,HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/testuser")
    public User test(){
        User user=userRepository.findByUsername("tuan");
        return user;
    }
}
