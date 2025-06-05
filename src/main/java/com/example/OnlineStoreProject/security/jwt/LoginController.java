//package com.example.OnlineStoreProject.security.jwt;
//
//import com.example.OnlineStoreProject.models.User;
//import io.jsonwebtoken.Jwts;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class LoginController {
//
//    private final AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        // this token is different from jwt
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                user.getUsername(),
//                user.getPassword()
//        );
//
//        //this will fault if credentials not valid
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = JwtUtil.generateToken((User) authentication.getPrincipal());
//        return ResponseEntity.ok(jwt);
//    }
//}
