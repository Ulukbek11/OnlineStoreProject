package com.example.OnlineStoreProject.repositories;

import com.example.OnlineStoreProject.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

     Optional<Address>  findByUserId(Long id);
}
