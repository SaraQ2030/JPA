package org.example.amazonupdate.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.amazonupdate.API.ApiRessponse;
import org.example.amazonupdate.Model.MerchantStock;
import org.example.amazonupdate.Service.MerchantStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("api/v1/amazone/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {
        private final MerchantStockService merchantStockService;


        @GetMapping("/get")
        public ResponseEntity getMerchantStock() {
      return ResponseEntity.status(200).body(merchantStockService.getAllMerchantStock());
        }

        @PostMapping("/add")
        public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {

            if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }

            merchantStockService.addMerchantStock(merchantStock);
            return ResponseEntity.status(200).body(new ApiRessponse("Add successfully"));
            }


        @PutMapping("/update/{id}")
        public ResponseEntity updateMerchantStock(@PathVariable int id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
            if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            boolean isUpdate = merchantStockService.updateMerchantStock(id, merchantStock);
            if (isUpdate) {
                return ResponseEntity.status(200).body(new ApiRessponse("merchant stock updated"));
            }
            return ResponseEntity.status(400).body("cannot update");
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity deleteMerchantStock(@PathVariable int id) {
            boolean isDeleted = merchantStockService.deleteMerchantStock(id);
            if (isDeleted) {
                return ResponseEntity.status(200).body(new ApiRessponse("merchant stock Deleted"));
            }
            return ResponseEntity.status(400).body(new ApiRessponse("not found id "));
        }

    //controller for add user product to the stock;
    @PutMapping("/refill/{m_id}/{p_id}/{amount}")
    public ResponseEntity addToStock(@PathVariable int m_id,@PathVariable int p_id,@PathVariable int amount){
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(400).body(message);
//        }
        boolean isRefill = merchantStockService.addToStock(m_id,p_id,amount);
        if (isRefill) {
            return ResponseEntity.status(200).body(new ApiRessponse("stock Refilled "));
        }
        return ResponseEntity.status(400).body("cannot refill the stock");
    }



    }
