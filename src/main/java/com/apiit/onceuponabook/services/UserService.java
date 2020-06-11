package com.apiit.onceuponabook.services;

import com.apiit.onceuponabook.dtos.AddressDTO;
import com.apiit.onceuponabook.dtos.UserDTO;
import com.apiit.onceuponabook.enums.UserRole;
import com.apiit.onceuponabook.models.Address;
import com.apiit.onceuponabook.models.User;
import com.apiit.onceuponabook.repositories.AddressRepository;
import com.apiit.onceuponabook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    AddressRepository addressRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private ModelToDTO modelToDTO;

    public ResponseEntity<Boolean> registerUser(User newUser){
        Optional<User> userOptional=userRepo.findById(newUser.getEmail());
        if(!userOptional.isPresent()){
            newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
            userRepo.save(newUser);
            return new ResponseEntity<>(true,HttpStatus.OK);

        }
        return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<UserDTO> getUser(String id){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return new ResponseEntity<>(modelToDTO.userToDTO(user),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<UserDTO>> getUserList() {
        List<UserDTO> userDTOList = new ArrayList<>();
        Iterable<User> userList = userRepo.findAll();
        for(User user : userList){
            userDTOList.add(modelToDTO.userToDTO(user));
        }
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    public ResponseEntity<Boolean> deleteUser(String id){
        try{
            userRepo.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (EmptyResultDataAccessException erda_ex){

        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> updateUser(String id, User newUser){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setPhoneNo(newUser.getPhoneNo());
            user.setDateOfBirth(newUser.getDateOfBirth());
            user.setAddresses(newUser.getAddresses());
            user.setEmail(newUser.getEmail());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            newUser = userRepo.save(newUser);
            return new ResponseEntity<>(modelToDTO.userToDTO(newUser), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<AddressDTO>> getAddressList(String id){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            List<AddressDTO> addressDTOList = new ArrayList<>();
            List<Address> addressList = addressRepo.findByUser(userOptional.get());
            for(Address add: addressList){
                addressDTOList.add(modelToDTO.addressToDTO(add));
            }
            return new ResponseEntity<>(addressDTOList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<AddressDTO> addNewAddress(Address newAddress){
        Optional<User> userOptional = userRepo.findById(newAddress.getUser().getEmail());
        if(userOptional.isPresent()){
            newAddress.setUser(userOptional.get());
            newAddress = addressRepo.save(newAddress);
            return new ResponseEntity<>(modelToDTO.addressToDTO(newAddress), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<AddressDTO> updateAddress(Address updateAddress){
        Optional<Address> addressOptional = addressRepo.findById(updateAddress.getId());
        if(addressOptional.isPresent()){
            Address address = addressOptional.get();

            address.setAddress(updateAddress.getAddress());
            address.setCity(updateAddress.getCity());
            address.setCountry(updateAddress.getCountry());
            address.setPostalCode(updateAddress.getPostalCode());
            addressRepo.save(address);
            return new ResponseEntity<>(modelToDTO.addressToDTO(address),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Boolean> deleteAddress(int id){
        try{
            addressRepo.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (EmptyResultDataAccessException erda_ex){

        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> updatePassword(String id, String currentPsw, String newPsw){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(bcryptEncoder.matches(currentPsw, user.getPassword())) {
                user.setPassword(bcryptEncoder.encode(newPsw));
                userRepo.save(user);
                return new ResponseEntity<>(modelToDTO.userToDTO(user), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //WISHLIST




}