//package com.example.OnlineStoreProject.security;
//
//import com.example.OnlineStoreProject.models.Address;
//import com.example.OnlineStoreProject.models.Cart;
//import com.example.OnlineStoreProject.models.CartItem;
//import com.example.OnlineStoreProject.models.User;
//import com.example.OnlineStoreProject.repositories.AddressRepository;
//import com.example.OnlineStoreProject.repositories.CartItemRepository;
//import com.example.OnlineStoreProject.repositories.CartRepository;
//import com.example.OnlineStoreProject.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@RestController
//public class RegisterController {
//
//    private final PasswordEncoder passwordEncoder;
//
//    private final UserRepository userRepository;
//    private final CartRepository cartRepository;
//    private final AddressRepository addressRepository;
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        Optional<User> userOptional = (userRepository.findByEmail(user.getEmail()));
//        if (userOptional.isEmpty()) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userRepository.save(user);
//
//            Cart cart = new Cart();
//            cart.setUser(user);
//            cart.setCartItems(new ArrayList<CartItem>());
//            cartRepository.save(cart);
//
//            Address address = new Address();
//            address.setUser(user);
//            address.setId(user.getId());
//            addressRepository.save(address);
//
//            return ResponseEntity.ok("User registered successfully");
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
//    }
//}
