package org.example.amazonupdate.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.amazonupdate.API.ApiRessponse;
import org.example.amazonupdate.Model.Product;
import org.example.amazonupdate.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/amazone/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProduct(){
        return ResponseEntity.status(200).body(productService.getAllProduct());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
  productService.addProduct(product);
      return ResponseEntity.status(200).body(new ApiRessponse("Add successfully"));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable int id,@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate= productService.updateProduct(id,product);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiRessponse("product updated"));
        }
        return ResponseEntity.status(400).body("cannot update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id){
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiRessponse("product Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiRessponse("Cannot find id "));
    }

    @GetMapping("/filter/{min}/{max}")
    public ResponseEntity filter(@PathVariable int min ,@PathVariable int max){
        List<Product> list=productService.filterPrice(min,max);
        if (list.isEmpty()){
            return ResponseEntity.status(400).body(new ApiRessponse("empty list "));
        }
        return ResponseEntity.status(200).body(list);
    }
}
