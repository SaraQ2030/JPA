package org.example.amazonupdate.Repository;

import org.example.amazonupdate.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
