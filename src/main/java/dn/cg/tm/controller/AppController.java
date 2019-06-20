package dn.cg.tm.controller;

import dn.cg.tm.domain.User;
import dn.cg.tm.repository.UserRepository;
import dn.cg.tm.security.JwtResponse;
import dn.cg.tm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    @GetMapping("/operator/test")
    public ResponseEntity<?> sayUser() {
        return new ResponseEntity<>("Welcome operator to my website", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken=jwtTokenProvider.generateToken(authentication);
        UserDetails userDetails =(UserDetails) authentication.getPrincipal();
        return new ResponseEntity<>( new JwtResponse(jwtToken,userDetails.getUsername(),userDetails.getAuthorities()),HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/testuser")
    public User test(){
        Date now = new Date();
        System.out.println(now);
        Date expiration = new Date(now.getTime()+3600000);
        System.out.println(expiration);
        User user=userRepository.findByUsername("tuan");
        return user;
    }
    @ResponseBody
    @GetMapping("/testauthor")
    public String testAuthor(Principal principal){
        Principal a = principal;
        return principal.getName();
    }
}
