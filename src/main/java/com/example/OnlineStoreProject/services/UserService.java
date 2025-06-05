package com.example.OnlineStoreProject.services;

import com.example.OnlineStoreProject.dto.UserDto;
import com.example.OnlineStoreProject.models.Address;
import com.example.OnlineStoreProject.models.Cart;
import com.example.OnlineStoreProject.models.CartItem;
import com.example.OnlineStoreProject.models.User;
import com.example.OnlineStoreProject.repositories.AddressRepository;
import com.example.OnlineStoreProject.repositories.CartRepository;
import com.example.OnlineStoreProject.repositories.UserRepository;
import com.example.OnlineStoreProject.security.jwt.JwtUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<String> register(User user) {
        Optional<User> userOptional = (userRepository.findByEmail(user.getEmail()));
        if (userOptional.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            Cart cart = new Cart();
            cart.setUser(user);
            cart.setCartItems(new ArrayList<CartItem>());
            cartRepository.save(cart);

            Address address = new Address();
            address.setUser(user);
            address.setId(user.getId());
            addressRepository.save(address);

            return ResponseEntity.ok("User registered successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
    }


        public ResponseEntity<String> login(User user) {
            // this token is different from jwt
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()
            );

            //this will fault if credentials not valid
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = JwtUtil.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(jwt);
        }


    public ResponseEntity<List<UserDto>>  getAll() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(user -> new UserDto(user.getId(), user)).toList());
    }

    public ResponseEntity<UserDto> getById(Long id) {
        return ResponseEntity.ok(new UserDto(
                id,
                userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not found")))) ;
    }


    public ResponseEntity<String> delete(Long id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    public ResponseEntity<User> update(Long id, User newUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));

        if (newUser.getFullname() != null) {user.setFullname(newUser.getFullname());}
        if (newUser.getPassword() != null) {user.setPassword(newUser.getPassword());}
        if (newUser.getEmail() != null) {user.setEmail(newUser.getEmail());}
        if (newUser.getPhone() != null) {user.setPhone(newUser.getPhone());}
        if (newUser.getRole() != null) {user.setRole(newUser.getRole());}

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }





}
