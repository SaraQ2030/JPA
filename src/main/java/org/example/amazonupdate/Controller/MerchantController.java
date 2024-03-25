package org.example.amazonupdate.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.amazonupdate.API.ApiRessponse;
import org.example.amazonupdate.Model.Merchant;
import org.example.amazonupdate.Service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/amazone/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchant(){
        return ResponseEntity.status(200).body(  merchantService.getMarche());
    }


    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiRessponse("Add successfully"));
           }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable int id,@RequestBody @Valid Merchant merchant, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate= merchantService.updateMerchant(id,merchant);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiRessponse("merchant updated"));
        }
        return ResponseEntity.status(400).body("Not found");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id){
        boolean isDeleted = merchantService.deleteMerchant(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiRessponse("merchant Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiRessponse("Cannot found id "));
    }

}
