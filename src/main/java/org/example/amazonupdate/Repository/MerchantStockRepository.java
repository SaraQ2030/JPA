package org.example.amazonupdate.Repository;

import org.example.amazonupdate.Model.MerchantStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantStockRepository extends JpaRepository<MerchantStock,Integer> {
}
