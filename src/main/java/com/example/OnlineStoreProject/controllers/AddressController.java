package com.example.OnlineStoreProject.controllers;

import com.example.OnlineStoreProject.models.Address;
import com.example.OnlineStoreProject.services.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{id}/address")
public class AddressController {

    private final AddressService addressService;

//    @PostMapping
//    public ResponseEntity<Address> addAddress(@PathVariable Long id, @RequestBody Address address) {
//        return addressService.save(id, address);
//    }

    @PutMapping
    public ResponseEntity<Address> updateAddress(@PathVariable Long id,
                                                 @RequestBody @Valid Address address) {
        return addressService.update(id, address);
    }

//    @DeleteMapping("/{addrId}")
//    public ResponseEntity<String> deleteAddress(@PathVariable Long id,
//                                                @PathVariable Long addrId) {
//        return addressService.delete(addrId);
//    }

    @GetMapping
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {
        return addressService.findByUser(id);
    }


}
