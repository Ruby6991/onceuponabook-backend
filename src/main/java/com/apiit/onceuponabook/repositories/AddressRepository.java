package com.apiit.onceuponabook.repositories;

import com.apiit.onceuponabook.models.Address;
import com.apiit.onceuponabook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByUser(User user);
}
