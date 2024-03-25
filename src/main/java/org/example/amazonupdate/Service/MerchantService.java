package org.example.amazonupdate.Service;

import lombok.RequiredArgsConstructor;
import org.example.amazonupdate.Model.Merchant;
import org.example.amazonupdate.Repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantRepository;
    public List<Merchant> getMarche(){
        return merchantRepository.findAll();
    }
    public void addMerchant(Merchant merchant){
        merchantRepository.save(merchant);
    }

    public boolean updateMerchant(int id, Merchant merchant){
        Merchant m=merchantRepository.getReferenceById(id);
            if (merchantRepository.existsById(id)){
               m.setName(merchant.getName());
                return true;
            }
            return false;
    }
    public boolean deleteMerchant(int id){
        Merchant m=merchantRepository.getReferenceById(id);
            if (merchantRepository.existsById(id)){
            merchantRepository.delete(m);
                return true;
            }
         return false;
    }
}
