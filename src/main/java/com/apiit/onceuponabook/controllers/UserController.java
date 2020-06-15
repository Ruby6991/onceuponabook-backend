package com.apiit.onceuponabook.controllers;

import com.apiit.onceuponabook.dtos.BookDTO;
import com.apiit.onceuponabook.dtos.UserDTO;
import com.apiit.onceuponabook.models.Book;
import com.apiit.onceuponabook.models.User;
import com.apiit.onceuponabook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/Register")
    public ResponseEntity<Boolean> RegisterUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("/GetUser")
    public ResponseEntity<UserDTO> GetUserDetails(@RequestBody User user){
        return userService.getUser(user.getEmail());
    }

    @PostMapping("/GetUserList")
    public ResponseEntity<List<UserDTO>> getUserList(){
        return userService.getUserList();
    }

    @DeleteMapping("/DeleteUser/{id}")
    public ResponseEntity<Boolean> DeleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }

    @PutMapping("/UpdateUser/{id}")
    public ResponseEntity<UserDTO> UpdateUser(@PathVariable String id, @RequestBody User user){
        return userService.updateUser(id,user);
    }

    @PutMapping("/UpdatePassword/{id}")
    public ResponseEntity<UserDTO> UpdatePassword(@PathVariable String id, @RequestParam(value="currentPsw") String currentPsw, @RequestParam(value="newPsw") String newPsw){
        return userService.updatePassword(id, currentPsw ,newPsw);
    }

    @PostMapping("/CreateWishlistItem/{id}")
    public ResponseEntity<Boolean> CreateWishlistItem(@PathVariable String id, @RequestBody Book wishBook){
        return userService.createWishlistItem(id,wishBook);
    }

    @PostMapping("/getWishList")
    public ResponseEntity<List<BookDTO>> getWishList(@RequestBody User user){
        return userService.getWishList(user);
    }
}
