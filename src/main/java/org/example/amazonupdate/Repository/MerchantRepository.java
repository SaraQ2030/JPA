package org.example.amazonupdate.Repository;

import org.example.amazonupdate.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant,Integer> {
}
