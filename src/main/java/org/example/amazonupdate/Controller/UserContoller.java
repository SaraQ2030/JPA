package org.example.amazonupdate.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.amazonupdate.API.ApiRessponse;
import org.example.amazonupdate.Model.Product;
import org.example.amazonupdate.Model.User;
import org.example.amazonupdate.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/amazone/user")
@RequiredArgsConstructor

public class UserContoller {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser(){
        return ResponseEntity.status(200).body(userService.getAllUser());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiRessponse("Add successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id,@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate= userService.updateUser(id,user);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiRessponse("User updated"));
        }
        return ResponseEntity.status(400).body("Not found id");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiRessponse("user Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiRessponse("Cannot find id "));
    }


    @PutMapping("/buy/{userId}/{marchantId}/{productId}/{dis}")
    public ResponseEntity buy(@PathVariable int userId,@PathVariable int marchantId,@PathVariable int productId,@PathVariable int dis){
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(400).body(message);
//        }
        boolean isBuy = userService.buyProduct(userId,marchantId,productId,dis);
        if (isBuy) {
            return ResponseEntity.status(200).body(new ApiRessponse("Buy product successfly"));
        }
        return ResponseEntity.status(400).body("cannot buy");
    }
    @DeleteMapping("/delete/{id}/{del}/{id_del}")
    public ResponseEntity adminDelete(@PathVariable int id,@PathVariable int del,@PathVariable int id_del){
        boolean isDeleted=userService.deleteAdmin(id,del,id_del);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiRessponse("Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiRessponse("Cannot find id "));
    }


    @PutMapping("/cart/{userId}/{marchantId}/{productId}/{num}")
    public ResponseEntity addCart(@PathVariable int userId,@PathVariable int marchantId,@PathVariable int productId,@PathVariable int num){
        List<Product> cart = userService.addToCart(userId,marchantId,productId,num);
        if (cart.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiRessponse("fail to add"));
        }
        return ResponseEntity.status(200).body(cart);
    }

    }
