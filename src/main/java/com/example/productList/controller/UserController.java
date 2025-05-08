package com.example.productList.controller;

import com.example.productList.dto.UserDTO;
import com.example.productList.entity.User;
import com.example.productList.security.JwtUtil;
import com.example.productList.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailService myUserDetailService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        return  new ResponseEntity<>(myUserDetailService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDTO.getUsername(), userDTO.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        UserDetails userDetails = myUserDetailService.loadUserByUsername(userDTO.getUsername());
        String genToken = jwtUtil.generateToken(userDetails.getUsername(), roles);
        return new ResponseEntity<>(genToken, HttpStatus.CREATED);
    }
}
