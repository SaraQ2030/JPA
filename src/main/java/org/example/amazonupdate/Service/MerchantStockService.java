package org.example.amazonupdate.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.amazonupdate.Model.Merchant;
import org.example.amazonupdate.Model.MerchantStock;
import org.example.amazonupdate.Model.Product;
import org.example.amazonupdate.Model.User;
import org.example.amazonupdate.Repository.MerchantRepository;
import org.example.amazonupdate.Repository.MerchantStockRepository;
import org.example.amazonupdate.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository;
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository ;
    public List<MerchantStock> getAllMerchantStock(){
        return merchantStockRepository.findAll();
    }

    public void addMerchantStock(MerchantStock merchantStock){
        merchantStockRepository.save(merchantStock);
    }
    public Boolean updateMerchantStock( Integer id,MerchantStock merchantStock){
        MerchantStock ms=merchantStockRepository.getById(id);

        if (ms==null){
            return false;
        }
        ms.setMerchant_id(merchantStock.getMerchant_id());
        ms.setStock(merchantStock.getStock());
        ms.setProduct_id(merchantStock.getProduct_id());
            merchantStockRepository.save(ms);
        return true;
    }


    public Boolean deleteMerchantStock(Integer id){
        MerchantStock ms=merchantStockRepository.getById(id);
        if (merchantStockRepository.existsById(id)) {
            merchantStockRepository.delete(ms);
            return true;
        }

        return false;}


    //11-user add to the stock
    public boolean addToStock(int merchant_id,int product_id,int amount){
        for (MerchantStock ms:merchantStockRepository.findAll()) {
            if (ms.getMerchant_id()==(merchant_id) && ms.getProduct_id()==product_id) {
                for (Merchant m:merchantRepository.findAll()){
                    if (m.getId()== merchant_id){
                        for (Product product: productRepository.findAll()){
                            if (product.getId()== product_id){
                                ms.setStock(ms.getStock() + amount);
                                merchantStockRepository.save(ms);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
