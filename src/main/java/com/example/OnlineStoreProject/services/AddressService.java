package com.example.OnlineStoreProject.services;

import com.example.OnlineStoreProject.exceptions.NotFoundException;
import com.example.OnlineStoreProject.models.Address;
import com.example.OnlineStoreProject.models.User;
import com.example.OnlineStoreProject.repositories.AddressRepository;
import com.example.OnlineStoreProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

//    public ResponseEntity<Address> save(Long id, Address address) {
//        Optional<User> user = userRepository.findById(id);
//        return ResponseEntity.ok(addressRepository.save(address));
//    }

    public ResponseEntity<Address> findByUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id));
        return ResponseEntity.ok(user.getAddress());
    }

//    public ResponseEntity<String> delete(Long addrId) {
//        addressRepository.findById(addrId).orElseThrow();
//        addressRepository.deleteById(addrId);
//        return ResponseEntity.ok("Address successfully deleted");
//    }

    public ResponseEntity<Address> update(Long id, Address newAddress) {
        Address address = addressRepository.findByUserId(id).orElseThrow(() -> new NotFoundException("User", id));
        User user = userRepository.findById(id).orElseThrow();
        if (newAddress.getHouseNumber() != null) {address.setHouseNumber(newAddress.getHouseNumber());}
        if (newAddress.getStreet() != null) {address.setStreet(newAddress.getStreet());}
        if (newAddress.getCity() != null) {address.setCity(newAddress.getCity());}
        if (newAddress.getCountry() != null) {address.setCountry(newAddress.getCountry());}
        if (newAddress.getPostalCode() != null) {address.setPostalCode(newAddress.getPostalCode());}
        user.setAddress(address);
        return ResponseEntity.ok(addressRepository.save(address));
    }


//    public ResponseEntity<Address> changeCurrentAddress(Long id, Address address) {
//        User user = userRepository.findById(id).orElseThrow();
//        for (Address oldAddr : user.getAddresses()) {
//            if (oldAddr.getAddressStatus().equals(address.getAddressStatus())) {
//                oldAddr.setAddressStatus(AddressStatus.NOT_SELECTED);
//            }
//        }
//        addressRepository.save(address);
//    }
}
