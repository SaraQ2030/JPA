package org.example.amazonupdate.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.amazonupdate.API.ApiRessponse;
import org.example.amazonupdate.Model.Category;
import org.example.amazonupdate.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/amazone/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory(){
        return ResponseEntity.status(200).body(categoryService.getCategory());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
      categoryService.addCategory(category);
      return ResponseEntity.status(200).body(new ApiRessponse("Add successfully"));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable int id,@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate= categoryService.updateCategory(id,category);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiRessponse("category updated"));
        }
        return ResponseEntity.status(400).body("cannot update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id){
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiRessponse("category Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiRessponse("not found id "));
    }
}
