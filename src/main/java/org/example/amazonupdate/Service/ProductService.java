package org.example.amazonupdate.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.amazonupdate.Model.Product;
import org.example.amazonupdate.Model.User;
import org.example.amazonupdate.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void addProduct(Product product){

        productRepository.save(product);
    }
    public Boolean updateProduct( Integer id,Product product){
        Product p=productRepository.getById(id);

        if (p==null){
            return false;
        }
        p.setName(product.getName());
        p.setPrice(p.getPrice());
        p.setCategory_id(product.getCategory_id());

       productRepository.save(p);
        return true;
    }


    public Boolean deleteProduct(Integer id){
        Product p=productRepository.getById(id);
        if (productRepository.existsById(id)) {
           productRepository.delete(p);
            return true;
        }

        return false;
    }

    //filter price on range defined
    public List<Product> filterPrice(int minPrice, int maxPrice) {
        List<Product> list = new ArrayList<>();
        for (Product p : productRepository.findAll()) {
            if (p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
                list.add(p);
            }
        }
        list.sort(Comparator.comparingDouble(Product::getPrice));
        return list;
    }

}
